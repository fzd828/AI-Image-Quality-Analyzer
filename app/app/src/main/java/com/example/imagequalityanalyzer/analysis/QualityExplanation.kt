package com.example.imagequalityanalyzer.analysis

object QualityExplanation {
    fun describe(
        metrics: QualityMetrics,
        sharpnessScore: Int,
        exposureScore: Int,
        contrastScore: Int,
        colorCastScore: Int,
        overallScore: Int
    ): String {
        val lowest = listOf(
            Issue.Sharpness to sharpnessScore,
            Issue.Exposure to exposureScore,
            Issue.Contrast to contrastScore,
            Issue.ColorCast to colorCastScore
        ).minBy { it.second }.first

        if (overallScore >= GOOD_OVERALL_SCORE && listOf(
                sharpnessScore,
                exposureScore,
                contrastScore,
                colorCastScore
            ).all { it >= ACCEPTABLE_SUB_SCORE }
        ) {
            return "Overall good quality. The image has balanced exposure, contrast, and color."
        }

        return when (lowest) {
            Issue.Sharpness -> "Main issue: the image may be blurry or lack fine edge detail."
            Issue.Exposure -> exposureExplanation(metrics)
            Issue.Contrast -> "Main issue: the image has low contrast, so details may look flat."
            Issue.ColorCast -> "Main issue: a color cast may be affecting the image white balance."
        }
    }

    private fun exposureExplanation(metrics: QualityMetrics): String =
        when {
            metrics.overexposedRatio > metrics.underexposedRatio ->
                "Main issue: the image appears overexposed with too many clipped bright pixels."
            metrics.underexposedRatio > metrics.overexposedRatio ->
                "Main issue: the image appears underexposed with too many clipped dark pixels."
            metrics.meanLuminance > BRIGHT_LUMINANCE ->
                "Main issue: the image appears too bright overall."
            else ->
                "Main issue: the image appears too dark overall."
        }

    private enum class Issue {
        Sharpness,
        Exposure,
        Contrast,
        ColorCast
    }

    private const val GOOD_OVERALL_SCORE = 80
    private const val ACCEPTABLE_SUB_SCORE = 60
    private const val BRIGHT_LUMINANCE = 128.0
}
