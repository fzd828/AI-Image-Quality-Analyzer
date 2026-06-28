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

## Completion Notes

- Status: completed locally on 2026-06-28.
- Implementation:
  - Added `ImageQualityScorer` with 0-100 sharpness, exposure, contrast, color cast, and weighted overall scores.
  - Added `QualityExplanation` to generate a short readable diagnosis from the weakest score dimension.
  - Wired `ImageAnalyzerScreen` to analyze the WS2 downsampled bitmap through the WS3 metrics engine, then display overall score, sub-scores, diagnosis, and elapsed analysis time.
- Scoring formula:
  - Overall score uses the planned weights: sharpness 35%, exposure 30%, contrast 20%, color cast 15%.
  - Sharpness uses a saturating Laplacian-variance normalization.
  - Exposure subtracts penalties for mean-luminance distance from neutral gray and clipped dark/bright pixel ratios.
  - Contrast maps luminance standard deviation toward a target standard deviation.
  - Color cast subtracts a penalty from RGB channel imbalance distance.
- Tests and verification:
  - TDD red run: `.\gradlew.bat :app:testDebugUnitTest --tests com.example.imagequalityanalyzer.analysis.ImageQualityScorerTest` failed before `ImageQualityScorer` existed with unresolved references.
  - Green run: `.\gradlew.bat :app:testDebugUnitTest --tests com.example.imagequalityanalyzer.analysis.ImageQualityScorerTest` succeeded.
  - Regression run: `.\gradlew.bat :app:testDebugUnitTest` succeeded.
  - Build run: `.\gradlew.bat :app:assembleDebug` succeeded.
- Notes:
  - No deep learning model, charts, export flow, or album management was added.
  - Real-device screenshot evidence remains part of WS6.
