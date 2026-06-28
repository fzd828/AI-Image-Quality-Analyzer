package com.example.imagequalityanalyzer.analysis

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ImageQualityScorerTest {
    @Test
    fun balancedMetricsProduceHighSubScoresAndWeightedOverallScore() {
        val result = ImageQualityScorer.score(
            QualityMetrics(
                laplacianVariance = 1_200.0,
                meanLuminance = 128.0,
                underexposedRatio = 0.0,
                overexposedRatio = 0.0,
                contrastStdDev = 55.0,
                colorCastDistance = 0.0
            )
        )

        assertEquals(75, result.sharpnessScore)
        assertEquals(100, result.exposureScore)
        assertEquals(100, result.contrastScore)
        assertEquals(100, result.colorCastScore)
        assertEquals(91, result.overallScore)
        assertTrue(result.explanation.contains("good", ignoreCase = true))
    }

    @Test
    fun clippedBrightImageReceivesLowExposureAndNamesOverexposureRisk() {
        val result = ImageQualityScorer.score(
            QualityMetrics(
                laplacianVariance = 800.0,
                meanLuminance = 245.0,
                underexposedRatio = 0.0,
                overexposedRatio = 0.45,
                contrastStdDev = 20.0,
                colorCastDistance = 10.0
            )
        )

        assertEquals(11, result.exposureScore)
        assertTrue(result.exposureScore < result.sharpnessScore)
        assertTrue(result.explanation.contains("overexposed", ignoreCase = true))
    }

    @Test
    fun colorCastDistanceLowersColorCastScoreAndDiagnosisNamesColorCast() {
        val result = ImageQualityScorer.score(
            QualityMetrics(
                laplacianVariance = 2_000.0,
                meanLuminance = 128.0,
                underexposedRatio = 0.0,
                overexposedRatio = 0.0,
                contrastStdDev = 60.0,
                colorCastDistance = 72.0
            )
        )

        assertEquals(10, result.colorCastScore)
        assertTrue(result.explanation.contains("color cast", ignoreCase = true))
    }
}
