package com.example.imagequalityanalyzer.analysis

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ImageQualityAnalyzerTest {
    @Test
    fun uniformGrayImageHasNeutralDeterministicMetrics() {
        val pixels = solidPixels(width = 4, height = 4, red = 128, green = 128, blue = 128)

        val first = ImageQualityAnalyzer.analyze(width = 4, height = 4, argbPixels = pixels)
        val second = ImageQualityAnalyzer.analyze(width = 4, height = 4, argbPixels = pixels)

        assertEquals(first, second)
        assertEquals(0.0, first.laplacianVariance, TOLERANCE)
        assertEquals(0.0, first.focusedLaplacianVariance, TOLERANCE)
        assertEquals(0.0, first.tenengrad, TOLERANCE)
        assertEquals(128.0, first.meanLuminance, TOLERANCE)
        assertEquals(0.0, first.underexposedRatio, TOLERANCE)
        assertEquals(0.0, first.overexposedRatio, TOLERANCE)
        assertEquals(0.0, first.contrastStdDev, TOLERANCE)
        assertEquals(0.0, first.colorCastDistance, TOLERANCE)
    }

    @Test
    fun checkerboardImageHasHighSharpnessAndContrast() {
        val checkerboard = IntArray(4 * 4) { index ->
            val x = index % 4
            val y = index / 4
            if ((x + y) % 2 == 0) rgb(0, 0, 0) else rgb(255, 255, 255)
        }

        val metrics = ImageQualityAnalyzer.analyze(width = 4, height = 4, argbPixels = checkerboard)

        assertEquals(127.5, metrics.meanLuminance, TOLERANCE)
        assertEquals(0.5, metrics.underexposedRatio, TOLERANCE)
        assertEquals(0.5, metrics.overexposedRatio, TOLERANCE)
        assertEquals(127.5, metrics.contrastStdDev, TOLERANCE)
        assertTrue(metrics.laplacianVariance > 1_000_000.0)
        assertTrue(metrics.focusedLaplacianVariance > 1_000_000.0)
        assertEquals(0.0, metrics.colorCastDistance, TOLERANCE)
    }

    @Test
    fun strongEdgeImageHasHighTenengradSignal() {
        val pixels = IntArray(8 * 8) { index ->
            val x = index % 8
            if (x < 4) rgb(0, 0, 0) else rgb(255, 255, 255)
        }

        val metrics = ImageQualityAnalyzer.analyze(width = 8, height = 8, argbPixels = pixels)

        assertTrue(metrics.tenengrad > 100_000.0)
    }

    @Test
    fun localFocusedSubjectHasStrongerFocusedSharpnessThanGlobalSharpness() {
        val pixels = solidPixels(width = 16, height = 16, red = 128, green = 128, blue = 128)
        for (y in 6 until 10) {
            for (x in 6 until 10) {
                pixels[y * 16 + x] = if ((x + y) % 2 == 0) rgb(20, 20, 20) else rgb(235, 235, 235)
            }
        }

        val metrics = ImageQualityAnalyzer.analyze(width = 16, height = 16, argbPixels = pixels)

        assertTrue(metrics.laplacianVariance > 0.0)
        assertTrue(metrics.focusedLaplacianVariance > metrics.laplacianVariance * 2.0)
        assertTrue(metrics.tenengrad > 0.0)
    }

    @Test
    fun lowEdgeImageKeepsAllSharpnessSignalsLow() {
        val pixels = IntArray(12 * 12) { index ->
            val x = index % 12
            val value = 120 + x
            rgb(value, value, value)
        }

        val metrics = ImageQualityAnalyzer.analyze(width = 12, height = 12, argbPixels = pixels)

        assertTrue(metrics.laplacianVariance < 1.0)
        assertTrue(metrics.focusedLaplacianVariance < 1.0)
        assertTrue(metrics.tenengrad < 100.0)
    }

    @Test
    fun whiteImageIsDetectedAsFullyOverexposed() {
        val pixels = solidPixels(width = 3, height = 3, red = 255, green = 255, blue = 255)

        val metrics = ImageQualityAnalyzer.analyze(width = 3, height = 3, argbPixels = pixels)

        assertEquals(255.0, metrics.meanLuminance, TOLERANCE)
        assertEquals(0.0, metrics.underexposedRatio, TOLERANCE)
        assertEquals(1.0, metrics.overexposedRatio, TOLERANCE)
        assertEquals(0.0, metrics.contrastStdDev, TOLERANCE)
        assertEquals(0.0, metrics.colorCastDistance, TOLERANCE)
    }

    @Test
    fun colorBiasedImageReportsRgbImbalanceAfterIgnoringExtremePixels() {
        val pixels = intArrayOf(
            rgb(0, 0, 0),
            rgb(255, 255, 255),
            rgb(200, 100, 100),
            rgb(200, 100, 100)
        )

        val metrics = ImageQualityAnalyzer.analyze(width = 2, height = 2, argbPixels = pixels)

        assertEquals(128.7, metrics.meanLuminance, TOLERANCE)
        assertEquals(0.25, metrics.underexposedRatio, TOLERANCE)
        assertEquals(0.25, metrics.overexposedRatio, TOLERANCE)
        assertTrue(metrics.colorCastDistance > 80.0)
    }

    private fun solidPixels(width: Int, height: Int, red: Int, green: Int, blue: Int): IntArray =
        IntArray(width * height) { rgb(red, green, blue) }

    private fun rgb(red: Int, green: Int, blue: Int): Int =
        (0xFF shl 24) or (red shl 16) or (green shl 8) or blue

    private companion object {
        const val TOLERANCE = 0.0001
    }
}
