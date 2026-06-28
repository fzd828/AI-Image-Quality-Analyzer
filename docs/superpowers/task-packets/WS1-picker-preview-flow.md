# WS1: Picker Preview Flow（图片选择与预览流程）

## Purpose

Let the user select a local image and preview it in the Android app.

## Current Status

Draft code exists but is not yet reviewed or accepted.

Files already touched:

- `app/app/src/main/java/com/example/imagequalityanalyzer/MainActivity.kt`
- `app/app/src/main/java/com/example/imagequalityanalyzer/ui/ImageAnalyzerScreen.kt`

## Read First

- `AGENTS.md`
- `openspec/changes/option-1-image-quality-analyzer/specs/image-input-preview/spec.md`
- `.superpowers/sdd/task-2-brief.md`
- `.superpowers/sdd/task-2-report.md`

## Allowed Write Scope

- `app/app/src/main/java/com/example/imagequalityanalyzer/MainActivity.kt`
- `app/app/src/main/java/com/example/imagequalityanalyzer/ui/ImageAnalyzerScreen.kt`
- `.superpowers/sdd/task-2-report.md`
- `.superpowers/sdd/progress.md`

## Do Not Do

- Do not implement metadata display.
- Do not implement format detection.
- Do not implement downsampling.
- Do not implement scoring.
- Do not add Coil unless there is a strong reason.

## Acceptance Criteria

- App uses Android picker or equivalent Activity Result API.
- Canceling selection does not crash.
- Selecting an image displays a preview.
- MainActivity delegates to `ImageAnalyzerScreen`.
- Build is verified or the exact blocker is documented.
- A reviewer can decide whether WS1 is complete.
