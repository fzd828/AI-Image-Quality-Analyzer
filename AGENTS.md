# Image Quality Analyzer Project Instructions

## Project Context

This project is for ISBG 2026 AI Homework Contest Option 1: an Android image quality analysis tool.

The working strategy is:

1. Superpowers brainstorming confirmed the project strategy and design.
2. OpenSpec proposal/design/specs/tasks locked the acceptance criteria.
3. Superpowers writing-plans defines named work packages.
4. Future conversations should execute one named work package at a time.
5. Every implementation task must produce evidence for the final contest submission.

## Source Of Truth

Read these first before doing task work:

- `openspec/project.md`
- `openspec/changes/option-1-image-quality-analyzer/proposal.md`
- `openspec/changes/option-1-image-quality-analyzer/design.md`
- `openspec/changes/option-1-image-quality-analyzer/tasks.md`
- `docs/superpowers/plans/2026-06-28-workstream-execution-map.md`
- The specific file under `docs/superpowers/task-packets/` for the assigned work package.

## Execution Rules

- Do not implement unrelated features.
- Do not add deep learning models, complex charts, JSON export, account systems, cloud APIs, or full album management unless the plan is changed first.
- Use Android native Kotlin + Jetpack Compose.
- Core supported formats are JPEG, PNG, and WebP.
- HEIC is documentation-only unless explicitly promoted later.
- Primary final submission is a structured ZIP package. GitHub is optional after ZIP completion.
- Every task should update evidence or documentation when relevant.
- If a task is blocked, write the blocker into the relevant task packet or `.superpowers/sdd/progress.md`.

## Current Status Snapshot

- Android project skeleton exists under `app/`.
- `:app:assembleDebug` has been verified successfully with escalated filesystem access.
- Task 1 was reviewed and approved.
- Task 2 image picker/preview has draft code in:
  - `app/app/src/main/java/com/example/imagequalityanalyzer/MainActivity.kt`
  - `app/app/src/main/java/com/example/imagequalityanalyzer/ui/ImageAnalyzerScreen.kt`
- Task 2 has not been reviewed or accepted yet.

