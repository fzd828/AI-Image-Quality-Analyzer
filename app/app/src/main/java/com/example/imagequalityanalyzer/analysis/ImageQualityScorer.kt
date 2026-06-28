package com.example.imagequalityanalyzer.analysis

import kotlin.math.abs
import kotlin.math.roundToInt

data class QualityResult(
    val sharpnessScore: Int,
    val exposureScore: Int,
    val contrastScore: Int,
    val colorCastScore: Int,
    val overallScore: Int,
    val explanation: String
)

object ImageQualityScorer {
    fun score(metrics: QualityMetrics): QualityResult {
        val sharpnessScore = sharpnessScore(metrics.laplacianVariance)
        val exposureScore = exposureScore(metrics)
        val contrastScore = contrastScore(metrics.contrastStdDev)
        val colorCastScore = colorCastScore(metrics.colorCastDistance)
        val overallScore = weightedOverallScore(
            sharpnessScore = sharpnessScore,
            exposureScore = exposureScore,
            contrastScore = contrastScore,
            colorCastScore = colorCastScore
        )

        return QualityResult(
            sharpnessScore = sharpnessScore,
            exposureScore = exposureScore,
            contrastScore = contrastScore,
            colorCastScore = colorCastScore,
            overallScore = overallScore,
            explanation = QualityExplanation.describe(
                metrics = metrics,
                sharpnessScore = sharpnessScore,
                exposureScore = exposureScore,
                contrastScore = contrastScore,
                colorCastScore = colorCastScore,
                overallScore = overallScore
            )
        )
    }

    private fun sharpnessScore(laplacianVariance: Double): Int =
        normalizedScore(laplacianVariance, laplacianVariance + SHARPNESS_HALF_SCORE)

    private fun exposureScore(metrics: QualityMetrics): Int {
        val brightnessPenalty = abs(metrics.meanLuminance - IDEAL_LUMINANCE) / IDEAL_LUMINANCE *
            BRIGHTNESS_PENALTY_WEIGHT
        val clippingPenalty = (metrics.underexposedRatio + metrics.overexposedRatio) *
            CLIPPING_PENALTY_WEIGHT
        return scoreFromPenalty(brightnessPenalty + clippingPenalty)
    }

    private fun contrastScore(contrastStdDev: Double): Int =
        normalizedScore(contrastStdDev, TARGET_CONTRAST_STD_DEV)

    private fun colorCastScore(colorCastDistance: Double): Int =
        scoreFromPenalty(colorCastDistance / MAX_ACCEPTABLE_COLOR_CAST * 100.0)

    private fun weightedOverallScore(
        sharpnessScore: Int,
        exposureScore: Int,
        contrastScore: Int,
        colorCastScore: Int
    ): Int = (
        sharpnessScore * 0.35 +
            exposureScore * 0.30 +
            contrastScore * 0.20 +
            colorCastScore * 0.15
        ).roundToInt().coerceIn(SCORE_MIN, SCORE_MAX)

    private fun normalizedScore(value: Double, target: Double): Int {
        if (target <= 0.0) {
            return SCORE_MIN
        }
        return (value / target * SCORE_MAX).roundToInt().coerceIn(SCORE_MIN, SCORE_MAX)
    }

    private fun scoreFromPenalty(penalty: Double): Int =
        (SCORE_MAX - penalty).roundToInt().coerceIn(SCORE_MIN, SCORE_MAX)

    private const val SCORE_MIN = 0
    private const val SCORE_MAX = 100
    private const val SHARPNESS_HALF_SCORE = 400.0
    private const val IDEAL_LUMINANCE = 128.0
    private const val BRIGHTNESS_PENALTY_WEIGHT = 35.0
    private const val CLIPPING_PENALTY_WEIGHT = 127.0
    private const val TARGET_CONTRAST_STD_DEV = 55.0
    private const val MAX_ACCEPTABLE_COLOR_CAST = 80.0
}
