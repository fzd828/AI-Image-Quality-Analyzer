# Tasks: Option 1 Image Quality Analyzer

## Named Work Packages

These packages are the canonical execution units for future conversations.

- [x] **WS0 - Project Baseline And Build Verification（项目基线与构建验证）**
  - Android Kotlin + Jetpack Compose project exists under `app/`.
  - Debug build has been verified with `:app:assembleDebug`.
  - Representative Redmi K70E true-device evidence was later recorded during WS6 validation.
- [x] **WS1 - Picker Preview Flow（图片选择与预览流程）**
  - Depends on WS0.
  - Implements local image selection and preview only.
  - Current status: implemented, reviewed, and committed as `cad805e`.
- [x] **WS2 - Decode Metadata And Large Image Safety（图片解码、元数据与大图安全）**
  - Depends on WS1.
  - Adds image metadata, format detection, and safe downsampled analysis bitmap.
  - Current status: implemented in `ImageLoader`, wired into preview UI, verified with unit tests plus debug build, and committed as `7e41851`.
- [x] **WS3 - Quality Metrics Engine（画质指标计算引擎）**
  - Depends on WS0.
  - Can run in parallel with WS2 if it only writes `analysis/` code and tests.
  - Current status: implemented and committed as `a028db2`; Laplacian variance memory optimization committed as `f2ba977`.
- [x] **WS4 - Scoring And Result UI（评分规则与结果展示界面）**
  - Depends on WS2 and WS3.
  - Adds 0-100 sub-scores, overall score, explanation, and analysis time display.
  - Current status: implemented and committed as `d3b2d57`; result screen scroll/inset fix committed as `ad853b3`.
- [x] **WS5 - Redmi K70E Sample Set（红米 K70E 样本图片集）**
  - Depends on WS0.
  - Can run in parallel with implementation.
  - Prepares documented comparison samples and device evidence.
  - Current status: comparison sample set prepared and committed as `bbfa008`; current samples are documented Wikimedia/NIND/NASA external or public samples plus derived format samples. Redmi K70E evidence is representative true-device validation, not a claim that all samples are Redmi self-shot.
- [x] **WS6 - Validation Evidence Run（验证证据运行与整理）**
  - Depends on WS4 and WS5.
  - Produces CSV logs, screenshots, validation table, and counterexample analysis.
  - Current status: completed through `1153675 docs: refresh final validation evidence`. Final structured evidence is `logs/final_validation_log.csv`, with Android Emulator full category coverage and Redmi K70E representative true-device screenshots.
- [x] **WS7 - Contest Documentation Pack（比赛必交文档包）**
  - Depends on WS0 for drafts; final pass depends on WS6.
  - Completes README, algorithm notes, AI collaboration, constraints, and scoring alignment.
  - Current status: completed by the contest documentation finalization pass; WS8 remains separate.
- [ ] **WS8 - Final ZIP Submission Package（最终 ZIP 提交包）**
  - Depends on WS6 and WS7.
  - Builds final APK and structured ZIP. GitHub is optional only after ZIP completion.

Detailed package briefs live under `docs/superpowers/task-packets/`.

## Phase 0 - Submission Frame

- [x] Confirm app platform and development environment.
- [x] Create final repository/package structure.
- [x] Keep this task list updated as evidence of process through WS4.

## Phase 1 - Android MVP

- [x] Create Android project.
- [x] Implement image picker.
- [x] Preview selected image.
- [x] Show image metadata: format, dimensions, file size.
- [x] Support JPEG, PNG, WebP.
- [x] Add large-image safe decode/downsampling.
- [x] Record original image size, analysis bitmap size, and whether downsampling occurred.

## Phase 2 - Quality Metrics

- [x] Implement grayscale conversion.
- [x] Implement sharpness score.
- [x] Implement exposure score.
- [x] Implement contrast score.
- [x] Implement color cast score as the fourth quality dimension.
- [x] Implement weighted overall score.
- [x] Generate readable result explanation.
- [x] Record analysis time for each image.

## Phase 3 - Validation Evidence

- [x] Prepare sample categories: clear, blur, overexposed, underexposed, noisy.
- [x] Collect 2-3 images per category.
- [x] Run app against representative JPEG, PNG, and WebP samples on Android Emulator API 36.1.
- [ ] Run app against all samples on Redmi K70E true device. Current final evidence uses representative Redmi K70E validation, not an all-sample true-device sweep.
- [x] Record sub-scores, overall score, and manual judgment.
- [x] Add screenshots or logs.
- [x] Add at least one counterexample and explain why it fails.

## Phase 4 - Contest Documents

- [x] Complete README: goals, non-goals, completion criteria, run instructions.
- [x] Complete 500-word constraints and decisions note.
- [x] Complete validation evidence document.
- [x] Complete AI collaboration document.
- [x] Complete algorithm notes.
- [x] Complete counterexample analysis.
- [x] Add HEIC compatibility discussion without making it a minimum supported format.

## Phase 5 - Final Package

- [ ] Build APK.
- [ ] Verify install and run on real device or emulator.
- [ ] Check package naming rule.
- [ ] Ensure all required evidence is included.
- [ ] Create structured ZIP package as the primary submission.
- [ ] Publish to GitHub only if time remains after ZIP package completion.
- [ ] Final self-review against judging rubric.
