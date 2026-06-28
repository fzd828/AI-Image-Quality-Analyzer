# WS3: Quality Metrics Engine（画质指标计算引擎）

## Purpose

Implement raw quality metrics independent of the UI.

## Dependencies

- Can start after WS0.
- It should not depend on Android picker UI.

## Read First

- `AGENTS.md`
- `openspec/changes/option-1-image-quality-analyzer/specs/quality-analysis-scoring/spec.md`
- `deliverables/docs/algorithm-notes.md`

## Allowed Write Scope

- Create: `app/app/src/main/java/com/example/imagequalityanalyzer/analysis/ImageQualityAnalyzer.kt`
- Create: `app/app/src/test/java/com/example/imagequalityanalyzer/analysis/ImageQualityAnalyzerTest.kt`
- Optional helper files under `analysis/`

## Required Interface

```kotlin
data class QualityMetrics(
    val laplacianVariance: Double,
    val meanLuminance: Double,
    val underexposedRatio: Double,
    val overexposedRatio: Double,
    val contrastStdDev: Double,
    val colorCastDistance: Double
)
```

## Metrics

- Luminance: `0.299 * red + 0.587 * green + 0.114 * blue`
- Sharpness: Laplacian variance
- Exposure: mean luminance, underexposed ratio, overexposed ratio
- Contrast: luminance standard deviation
- Color cast: RGB channel imbalance after excluding extreme dark/bright pixels

## Acceptance Criteria

- Raw metrics are deterministic.
- Synthetic unit tests cover uniform gray, checkerboard, white, and color-biased images.
- No UI code is added.
- Known limitations are easy to document later.
