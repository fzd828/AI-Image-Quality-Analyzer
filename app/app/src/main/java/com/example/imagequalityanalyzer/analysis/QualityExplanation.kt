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
            return "整体画质较好，曝光、对比度和色彩表现较均衡。"
        }

        return when (lowest) {
            Issue.Sharpness -> "主要问题：图片可能模糊，或缺少细节边缘。"
            Issue.Exposure -> exposureExplanation(metrics)
            Issue.Contrast -> "主要问题：图片对比度偏低，细节可能显得发灰。"
            Issue.ColorCast -> "主要问题：图片可能存在偏色，影响白平衡表现。"
        }
    }

    private fun exposureExplanation(metrics: QualityMetrics): String =
        when {
            metrics.overexposedRatio > metrics.underexposedRatio ->
                "主要问题：图片可能过曝，高亮区域裁剪像素过多。"
            metrics.underexposedRatio > metrics.overexposedRatio ->
                "主要问题：图片可能欠曝，暗部区域裁剪像素过多。"
            metrics.meanLuminance > BRIGHT_LUMINANCE ->
                "主要问题：图片整体偏亮。"
            else ->
                "主要问题：图片整体偏暗。"
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
