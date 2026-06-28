# WS4: Scoring And Result UI（评分规则与结果展示界面）

## Purpose

Convert raw metrics into 0-100 sub-scores, overall score, readable explanation, and display them in the app.

## Dependencies

- WS2 and WS3 complete.

## Read First

- `AGENTS.md`
- `openspec/changes/option-1-image-quality-analyzer/specs/quality-analysis-scoring/spec.md`
- `docs/superpowers/task-packets/WS2-decode-metadata-large-image-safety.md`
- `docs/superpowers/task-packets/WS3-quality-metrics-engine.md`

## Allowed Write Scope

- Create: `app/app/src/main/java/com/example/imagequalityanalyzer/analysis/ImageQualityScorer.kt`
- Create: `app/app/src/main/java/com/example/imagequalityanalyzer/analysis/QualityExplanation.kt`
- Modify: `app/app/src/main/java/com/example/imagequalityanalyzer/ui/ImageAnalyzerScreen.kt`
- Optional tests under `app/app/src/test/java/com/example/imagequalityanalyzer/analysis/`

## Required Interface

```kotlin
data class QualityResult(
    val sharpnessScore: Int,
    val exposureScore: Int,
    val contrastScore: Int,
    val colorCastScore: Int,
    val overallScore: Int,
    val explanation: String
)
```

## Acceptance Criteria

- UI shows sub-scores and overall score.
- UI shows analysis time.
- UI shows a readable diagnosis sentence.
- Scoring formula is simple and explainable.
- No deep learning model is introduced.
