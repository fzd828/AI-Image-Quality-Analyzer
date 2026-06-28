package com.example.imagequalityanalyzer.analysis

import kotlin.math.sqrt

data class QualityMetrics(
    val laplacianVariance: Double,
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

        val responses = ArrayList<Double>((width - 2) * (height - 2))
        for (y in 1 until height - 1) {
            for (x in 1 until width - 1) {
                val center = y * width + x
                val response = luminance[center - width] +
                    luminance[center - 1] +
                    luminance[center + 1] +
                    luminance[center + width] -
                    4.0 * luminance[center]
                responses.add(response)
            }
        }

        val mean = responses.sum() / responses.size
        return responses.sumOf { response ->
            val delta = response - mean
            delta * delta
        } / responses.size
    }

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

    private const val UNDEREXPOSED_LUMINANCE = 20.0
    private const val OVEREXPOSED_LUMINANCE = 235.0
    private const val COLOR_CAST_MIN_LUMINANCE = 20.0
    private const val COLOR_CAST_MAX_LUMINANCE = 235.0
}
