package com.example.imagequalityanalyzer.analysis

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ImageQualityScorerTest {
    @Test
    fun balancedMetricsProduceHighSubScoresAndWeightedOverallScore() {
        val result = ImageQualityScorer.score(
            metrics(
                laplacianVariance = 1_200.0,
                focusedLaplacianVariance = 1_400.0,
                tenengrad = 16_000.0,
                meanLuminance = 128.0,
                underexposedRatio = 0.0,
                overexposedRatio = 0.0,
                contrastStdDev = 55.0,
                colorCastDistance = 0.0
            )
        )

        assertTrue(result.sharpnessScore >= 80)
        assertEquals(100, result.exposureScore)
        assertEquals(100, result.contrastScore)
        assertEquals(100, result.colorCastScore)
        assertTrue(result.overallScore >= 93)
        assertTrue(result.explanation.isNotBlank())
    }

    @Test
    fun clippedBrightImageReceivesLowExposureAndNamesOverexposureRisk() {
        val result = ImageQualityScorer.score(
            metrics(
                laplacianVariance = 800.0,
                focusedLaplacianVariance = 900.0,
                tenengrad = 12_000.0,
                meanLuminance = 245.0,
                underexposedRatio = 0.0,
                overexposedRatio = 0.45,
                contrastStdDev = 20.0,
                colorCastDistance = 10.0
            )
        )

        assertEquals(11, result.exposureScore)
        assertTrue(result.exposureScore < result.sharpnessScore)
        assertTrue(result.explanation.isNotBlank())
    }

    @Test
    fun colorCastDistanceLowersColorCastScoreAndDiagnosisNamesColorCast() {
        val result = ImageQualityScorer.score(
            metrics(
                laplacianVariance = 2_000.0,
                focusedLaplacianVariance = 2_200.0,
                tenengrad = 18_000.0,
                meanLuminance = 128.0,
                underexposedRatio = 0.0,
                overexposedRatio = 0.0,
                contrastStdDev = 60.0,
                colorCastDistance = 72.0
            )
        )

        assertEquals(10, result.colorCastScore)
        assertTrue(result.explanation.isNotBlank())
    }

    @Test
    fun localSharpnessCarriesMostSharpnessScore() {
        val oldGlobalOnly = ImageQualityScorer.score(
            metrics(
                laplacianVariance = 250.0,
                focusedLaplacianVariance = 250.0,
                tenengrad = 2_000.0
            )
        )
        val focusedSubject = ImageQualityScorer.score(
            metrics(
                laplacianVariance = 250.0,
                focusedLaplacianVariance = 1_200.0,
                tenengrad = 8_000.0
            )
        )

        assertTrue(focusedSubject.sharpnessScore >= oldGlobalOnly.sharpnessScore + 15)
        assertTrue(focusedSubject.sharpnessScore >= 60)
    }

    @Test
    fun lowEdgeImageStillReceivesLowSharpnessScore() {
        val result = ImageQualityScorer.score(
            metrics(
                laplacianVariance = 0.5,
                focusedLaplacianVariance = 0.5,
                tenengrad = 50.0
            )
        )

        assertTrue(result.sharpnessScore <= 5)
    }

    @Test
    fun tenengradCannotSingleHandedlyDominateSharpnessScore() {
        val result = ImageQualityScorer.score(
            metrics(
                laplacianVariance = 0.0,
                focusedLaplacianVariance = 0.0,
                tenengrad = 100_000.0
            )
        )

        assertTrue(result.sharpnessScore <= 15)
    }

    private fun metrics(
        laplacianVariance: Double,
        focusedLaplacianVariance: Double = laplacianVariance,
        tenengrad: Double = 0.0,
        meanLuminance: Double = 128.0,
        underexposedRatio: Double = 0.0,
        overexposedRatio: Double = 0.0,
        contrastStdDev: Double = 55.0,
        colorCastDistance: Double = 0.0
    ): QualityMetrics =
        QualityMetrics(
            laplacianVariance = laplacianVariance,
            focusedLaplacianVariance = focusedLaplacianVariance,
            tenengrad = tenengrad,
            meanLuminance = meanLuminance,
            underexposedRatio = underexposedRatio,
            overexposedRatio = overexposedRatio,
            contrastStdDev = contrastStdDev,
            colorCastDistance = colorCastDistance
        )
}
