# AI Image Quality Analyzer

Android local image quality analyzer with reproducible validation evidence for ISBG 2026 AI Homework Option 1.

This repository contains the reproducible source code and evidence package for an Android Kotlin + Jetpack Compose image quality analysis tool. The app loads local images, previews metadata, safely downsamples large images, and reports explainable quality scores for sharpness, exposure, contrast, and color cast/noise risk.

## Start Here

- Contest delivery README: [deliverables/README.md](deliverables/README.md)
- Reproducibility notes: [deliverables/docs/reproducibility.md](deliverables/docs/reproducibility.md)
- Algorithm notes: [deliverables/docs/algorithm-notes.md](deliverables/docs/algorithm-notes.md)
- Validation evidence: [deliverables/docs/validation-evidence.md](deliverables/docs/validation-evidence.md)
- Counterexamples: [deliverables/docs/counterexamples.md](deliverables/docs/counterexamples.md)
- Final validation log: [logs/final_validation_log.csv](logs/final_validation_log.csv)

## Repository Layout

- `app/`: Android source project
- `apk/`: final debug APK copy for the submission package
- `deliverables/`: contest-facing documentation
- `samples/`: validation samples and source notes
- `screenshots/`: emulator and Redmi K70E evidence screenshots
- `logs/`: structured validation records
- `openspec/`: proposal, design, specs, and task trace

## Build

From the `app/` directory:

```powershell
.\gradlew.bat --console=plain :app:testDebugUnitTest :app:assembleDebug
```

The primary contest submission remains the structured ZIP package; this GitHub repository is a reproducible code and evidence mirror.
