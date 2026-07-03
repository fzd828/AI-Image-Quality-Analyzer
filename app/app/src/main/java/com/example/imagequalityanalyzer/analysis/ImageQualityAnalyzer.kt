package com.example.imagequalityanalyzer.analysis

import kotlin.math.sqrt

data class QualityMetrics(
    val laplacianVariance: Double,
    val focusedLaplacianVariance: Double,
    val tenengrad: Double,
    val meanLuminance: Double,
    val underexposedRatio: Double,
    val overexposedRatio: Double,
    val contrastStdDev: Double,
    val colorCastDistance: Double
)

object ImageQualityAnalyzer {
    fun analyze(width: Int, height: Int, argbPixels: IntArray): QualityMetrics {
        require(width > 0) { "width must be positive" }
        require(height > 0) { "height must be positive" }
        require(argbPixels.size == width * height) {
            "argbPixels size must equal width * height"
        }

        val luminance = DoubleArray(argbPixels.size)
        var luminanceSum = 0.0
        var underexposedCount = 0
        var overexposedCount = 0

        argbPixels.forEachIndexed { index, pixel ->
            val value = luminanceOf(pixel)
            luminance[index] = value
            luminanceSum += value
            if (value <= UNDEREXPOSED_LUMINANCE) {
                underexposedCount += 1
            }
            if (value >= OVEREXPOSED_LUMINANCE) {
                overexposedCount += 1
            }
        }

        val pixelCount = argbPixels.size
        val meanLuminance = luminanceSum / pixelCount

        return QualityMetrics(
            laplacianVariance = laplacianVariance(width, height, luminance),
            focusedLaplacianVariance = focusedLaplacianVariance(width, height, luminance),
            tenengrad = tenengrad(width, height, luminance),
            meanLuminance = meanLuminance,
            underexposedRatio = underexposedCount.toDouble() / pixelCount,
            overexposedRatio = overexposedCount.toDouble() / pixelCount,
            contrastStdDev = standardDeviation(luminance, meanLuminance),
            colorCastDistance = colorCastDistance(argbPixels)
        )
    }

    private fun luminanceOf(pixel: Int): Double =
        0.299 * red(pixel) + 0.587 * green(pixel) + 0.114 * blue(pixel)

    private fun laplacianVariance(width: Int, height: Int, luminance: DoubleArray): Double {
        if (width < 3 || height < 3) {
            return 0.0
        }

        val variance = VarianceAccumulator()

        for (y in 1 until height - 1) {
            for (x in 1 until width - 1) {
                val center = y * width + x
                variance.add(laplacianResponse(width, luminance, center))
            }
        }

        return variance.populationVariance()
    }

    private fun focusedLaplacianVariance(width: Int, height: Int, luminance: DoubleArray): Double {
        if (width < 3 || height < 3) {
            return 0.0
        }

        val responseWidth = width - 2
        val responseHeight = height - 2
        if (responseWidth < FOCUS_GRID_SIZE || responseHeight < FOCUS_GRID_SIZE) {
            return laplacianVariance(width, height, luminance)
        }

        val blockVariances = ArrayList<Double>(FOCUS_GRID_SIZE * FOCUS_GRID_SIZE)
        for (blockY in 0 until FOCUS_GRID_SIZE) {
            val startY = blockY * responseHeight / FOCUS_GRID_SIZE
            val endY = (blockY + 1) * responseHeight / FOCUS_GRID_SIZE
            for (blockX in 0 until FOCUS_GRID_SIZE) {
                val startX = blockX * responseWidth / FOCUS_GRID_SIZE
                val endX = (blockX + 1) * responseWidth / FOCUS_GRID_SIZE

                val blockVariance = laplacianVarianceInResponseBlock(
                    width = width,
                    luminance = luminance,
                    startResponseX = startX,
                    endResponseX = endX,
                    startResponseY = startY,
                    endResponseY = endY
                )
                blockVariances.add(blockVariance)
            }
        }

        val focusedBlockCount = (blockVariances.size * FOCUSED_BLOCK_RATIO)
            .toInt()
            .coerceAtLeast(1)
        return blockVariances
            .sortedDescending()
            .take(focusedBlockCount)
            .average()
    }

    private fun laplacianVarianceInResponseBlock(
        width: Int,
        luminance: DoubleArray,
        startResponseX: Int,
        endResponseX: Int,
        startResponseY: Int,
        endResponseY: Int
    ): Double {
        val variance = VarianceAccumulator()

        for (responseY in startResponseY until endResponseY) {
            val y = responseY + 1
            for (responseX in startResponseX until endResponseX) {
                val x = responseX + 1
                val center = y * width + x
                variance.add(laplacianResponse(width, luminance, center))
            }
        }

        return variance.populationVariance()
    }

    private fun tenengrad(width: Int, height: Int, luminance: DoubleArray): Double {
        if (width < 3 || height < 3) {
            return 0.0
        }

        var sumGradientEnergy = 0.0
        var count = 0
        for (y in 1 until height - 1) {
            for (x in 1 until width - 1) {
                val topLeft = luminance[(y - 1) * width + x - 1]
                val top = luminance[(y - 1) * width + x]
                val topRight = luminance[(y - 1) * width + x + 1]
                val left = luminance[y * width + x - 1]
                val right = luminance[y * width + x + 1]
                val bottomLeft = luminance[(y + 1) * width + x - 1]
                val bottom = luminance[(y + 1) * width + x]
                val bottomRight = luminance[(y + 1) * width + x + 1]

                val gradientX = -topLeft + topRight -
                    2.0 * left + 2.0 * right -
                    bottomLeft + bottomRight
                val gradientY = -topLeft - 2.0 * top - topRight +
                    bottomLeft + 2.0 * bottom + bottomRight

                sumGradientEnergy += gradientX * gradientX + gradientY * gradientY
                count += 1
            }
        }

        return sumGradientEnergy / count
    }

    private fun laplacianResponse(width: Int, luminance: DoubleArray, center: Int): Double =
        luminance[center - width] +
            luminance[center - 1] +
            luminance[center + 1] +
            luminance[center + width] -
            4.0 * luminance[center]

    private fun standardDeviation(values: DoubleArray, mean: Double): Double =
        sqrt(values.sumOf { value ->
            val delta = value - mean
            delta * delta
        } / values.size)

    private fun colorCastDistance(argbPixels: IntArray): Double {
        var redSum = 0.0
        var greenSum = 0.0
        var blueSum = 0.0
        var count = 0

        argbPixels.forEach { pixel ->
            val luminance = luminanceOf(pixel)
            if (luminance > COLOR_CAST_MIN_LUMINANCE && luminance < COLOR_CAST_MAX_LUMINANCE) {
                redSum += red(pixel)
                greenSum += green(pixel)
                blueSum += blue(pixel)
                count += 1
            }
        }

        if (count == 0) {
            return 0.0
        }

        val averageRed = redSum / count
        val averageGreen = greenSum / count
        val averageBlue = blueSum / count
        val neutral = (averageRed + averageGreen + averageBlue) / 3.0

        return sqrt(
            squared(averageRed - neutral) +
                squared(averageGreen - neutral) +
                squared(averageBlue - neutral)
        )
    }

    private fun squared(value: Double): Double = value * value

    private fun red(pixel: Int): Int = pixel ushr 16 and 0xFF

    private fun green(pixel: Int): Int = pixel ushr 8 and 0xFF

    private fun blue(pixel: Int): Int = pixel and 0xFF

    private class VarianceAccumulator {
        private var count = 0
        private var mean = 0.0
        private var sumSquaredDeltas = 0.0

        fun add(sample: Double) {
            count += 1
            val delta = sample - mean
            mean += delta / count
            sumSquaredDeltas += delta * (sample - mean)
        }

        fun populationVariance(): Double =
            if (count == 0) 0.0 else sumSquaredDeltas / count
    }

    private const val UNDEREXPOSED_LUMINANCE = 20.0
    private const val OVEREXPOSED_LUMINANCE = 235.0
    private const val COLOR_CAST_MIN_LUMINANCE = 20.0
    private const val COLOR_CAST_MAX_LUMINANCE = 235.0
    private const val FOCUS_GRID_SIZE = 8
    private const val FOCUSED_BLOCK_RATIO = 0.25
}
