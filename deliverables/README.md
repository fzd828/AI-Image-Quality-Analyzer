# Image Quality Analyzer

## Goal

Build a mobile tool that loads local images, previews them, analyzes image quality, and outputs an overall quality score with explainable sub-scores.

## Target Contest Topic

ISBG 2026 AI Homework Contest Option 1: AI image recognition and image quality analysis tool.

## Supported Formats

- JPEG
- PNG
- WebP

Optional discussion:

- HEIC compatibility depends on Android version and decoder availability.

## Quality Dimensions

- Sharpness / blur
- Exposure / brightness distribution
- Contrast
- Color cast or noise

## Non-Goals

- RAW support.
- Full album management.
- Professional photography-grade scoring.
- Account system or cloud service.
- Deep learning model deployment.

## Completion Criteria

- The app can run on a real Android device.
- The app can load and preview JPEG, PNG, and WebP images.
- The app can analyze a selected image and display sub-scores plus a 0-100 overall score.
- The app gives readable comments such as "slightly blurred, exposure normal, contrast low".
- Large images are decoded safely without OOM crashes.
- The package includes validation evidence and at least one counterexample.

## How To Run

To be filled after the Android project is created.

## Submission Evidence

- `docs/constraints-and-decisions.md`
- `docs/validation-evidence.md`
- `docs/ai-collaboration.md`
- `docs/algorithm-notes.md`
- `docs/counterexamples.md`

## Final Submission Form

The primary submission is a structured ZIP package containing:

- Android source project.
- Installable APK.
- Required documentation.
- Validation samples.
- Screenshots and logs.

Publishing the project to GitHub is optional and will be done only after the ZIP package is complete.
