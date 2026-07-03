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
        val issues = buildIssues(
            metrics = metrics,
            sharpnessScore = sharpnessScore,
            exposureScore = exposureScore,
            contrastScore = contrastScore,
            colorCastScore = colorCastScore
        )
        val judgment = overallJudgment(overallScore, issues)
        val problemText = if (issues.isEmpty()) {
            "未发现明显短板"
        } else {
            issues.joinToString(separator = "；") { it.summary }
        }
        val suggestion = issues.firstOrNull()?.suggestion ?: GOOD_IMAGE_SUGGESTION

        return "综合判断：$judgment。\n主要问题：$problemText。\n建议：$suggestion"
    }

    private fun buildIssues(
        metrics: QualityMetrics,
        sharpnessScore: Int,
        exposureScore: Int,
        contrastScore: Int,
        colorCastScore: Int
    ): List<DiagnosedIssue> {
        val scoreIssues = listOfNotNull(
            DiagnosedIssue(
                issue = Issue.Sharpness,
                score = sharpnessScore,
                summary = "清晰度偏低，可能模糊或细节边缘不足",
                suggestion = "拍摄时稳住设备并重新对焦，必要时提高快门速度"
            ).takeIf { sharpnessScore < LOW_SUB_SCORE },
            DiagnosedIssue(
                issue = Issue.Exposure,
                score = exposureScore,
                summary = exposureIssueSummary(metrics),
                suggestion = exposureSuggestion(metrics)
            ).takeIf { exposureScore < LOW_SUB_SCORE },
            DiagnosedIssue(
                issue = Issue.Contrast,
                score = contrastScore,
                summary = "对比度偏低，细节层次可能发灰",
                suggestion = "适度提升对比或选择更均匀的光线"
            ).takeIf { contrastScore < LOW_SUB_SCORE },
            DiagnosedIssue(
                issue = Issue.ColorCast,
                score = colorCastScore,
                summary = "偏色风险，白平衡可能不稳定",
                suggestion = "校正白平衡，避免强色光影响主体"
            ).takeIf { colorCastScore < LOW_SUB_SCORE }
        )

        val noiseRiskIssue = DiagnosedIssue(
            issue = Issue.NoiseRisk,
            score = POSSIBLE_NOISE_RISK_PRIORITY,
            summary = "暗部或纹理区域可能存在噪点干扰；当前算法只能作为疑似提示，不能单独确认噪声",
            suggestion = "低光环境下优先补光或降低 ISO，并结合人工查看原图"
        ).takeIf {
            hasPossibleNoiseRisk(
                metrics = metrics,
                sharpnessScore = sharpnessScore,
                exposureScore = exposureScore,
                contrastScore = contrastScore
            )
        }

        return (scoreIssues + listOfNotNull(noiseRiskIssue))
            .sortedWith(compareBy<DiagnosedIssue> { it.score }.thenBy { it.issue.ordinal })
            .take(MAX_ISSUES)
    }

    private fun overallJudgment(overallScore: Int, issues: List<DiagnosedIssue>): String =
        when {
            overallScore >= GOOD_OVERALL_SCORE && issues.isEmpty() -> "整体画质较好"
            overallScore >= USABLE_OVERALL_SCORE -> "整体可用但存在风险"
            else -> "整体质量偏低"
        }

    private fun exposureIssueSummary(metrics: QualityMetrics): String =
        when {
            metrics.overexposedRatio > metrics.underexposedRatio ->
                "曝光偏亮/过曝，高亮区域裁剪像素较多"
            metrics.underexposedRatio > metrics.overexposedRatio ->
                "曝光不足/欠曝，暗部区域裁剪像素较多"
            metrics.meanLuminance > BRIGHT_LUMINANCE ->
                "曝光偏亮，画面整体可能发白"
            else ->
                "曝光不足/欠曝，画面整体偏暗"
        }

    private fun exposureSuggestion(metrics: QualityMetrics): String =
        when {
            metrics.overexposedRatio > metrics.underexposedRatio ->
                "降低曝光或避开强直射高光，保留亮部细节"
            metrics.underexposedRatio > metrics.overexposedRatio ->
                "增加光线或曝光补偿，保留暗部细节"
            metrics.meanLuminance > BRIGHT_LUMINANCE ->
                "略微降低曝光，让主体亮度更接近中间调"
            else ->
                "增加环境光或曝光补偿，让主体亮度更接近中间调"
        }

    private fun hasPossibleNoiseRisk(
        metrics: QualityMetrics,
        sharpnessScore: Int,
        exposureScore: Int,
        contrastScore: Int
    ): Boolean {
        val highEdgeResponse = sharpnessScore >= HIGH_SHARPNESS_SCORE &&
            metrics.focusedLaplacianVariance >= HIGH_FOCUSED_LAPLACIAN &&
            metrics.tenengrad >= HIGH_TENENGRAD
        val darkOrLowExposure = metrics.underexposedRatio >= DARK_PIXEL_RATIO_RISK ||
            (exposureScore <= LOW_EXPOSURE_FOR_NOISE_RISK && metrics.meanLuminance <= DARK_LUMINANCE_RISK)
        val textureLikeContrast = contrastScore >= HIGH_CONTRAST_SCORE ||
            metrics.contrastStdDev >= HIGH_CONTRAST_STD_DEV

        return highEdgeResponse && darkOrLowExposure && textureLikeContrast
    }

    private data class DiagnosedIssue(
        val issue: Issue,
        val score: Int,
        val summary: String,
        val suggestion: String
    )

    private enum class Issue {
        Sharpness,
        Exposure,
        Contrast,
        ColorCast,
        NoiseRisk
    }

    private const val GOOD_OVERALL_SCORE = 80
    private const val USABLE_OVERALL_SCORE = 60
    private const val LOW_SUB_SCORE = 60
    private const val MAX_ISSUES = 3
    private const val BRIGHT_LUMINANCE = 128.0
    private const val HIGH_SHARPNESS_SCORE = 90
    private const val HIGH_FOCUSED_LAPLACIAN = 1_500.0
    private const val HIGH_TENENGRAD = 8_000.0
    private const val LOW_EXPOSURE_FOR_NOISE_RISK = 75
    private const val DARK_LUMINANCE_RISK = 100.0
    private const val DARK_PIXEL_RATIO_RISK = 0.12
    private const val HIGH_CONTRAST_SCORE = 80
    private const val HIGH_CONTRAST_STD_DEV = 50.0
    private const val POSSIBLE_NOISE_RISK_PRIORITY = LOW_SUB_SCORE - 1
    private const val GOOD_IMAGE_SUGGESTION = "保持当前拍摄条件，可直接作为质量较好的样本使用。"
}
