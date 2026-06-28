# Tasks: Option 1 Image Quality Analyzer

## Named Work Packages

These packages are the canonical execution units for future conversations.

- [x] **WS0 - Project Baseline And Build Verification（项目基线与构建验证）**
  - Android Kotlin + Jetpack Compose project exists under `app/`.
  - Debug build has been verified with `:app:assembleDebug`.
  - Real-device run remains pending until Redmi K70E is connected.
- [x] **WS1 - Picker Preview Flow（图片选择与预览流程）**
  - Depends on WS0.
  - Implements local image selection and preview only.
  - Current status: reviewer approved; committed separately from WS3.
- [x] **WS2 - Decode Metadata And Large Image Safety（图片解码、元数据与大图安全）**
  - Depends on WS1.
  - Adds image metadata, format detection, and safe downsampled analysis bitmap.
  - Current status: implemented in `ImageLoader`, wired into preview UI, and verified with unit tests plus debug build.
- [x] **WS3 - Quality Metrics Engine（画质指标计算引擎）**
  - Depends on WS0.
  - Can run in parallel with WS2 if it only writes `analysis/` code and tests.
- [x] **WS4 - Scoring And Result UI（评分规则与结果展示界面）**
  - Depends on WS2 and WS3.
  - Adds 0-100 sub-scores, overall score, explanation, and analysis time display.
- [ ] **WS5 - Redmi K70E Sample Set（红米 K70E 样本图片集）**
  - Depends on WS0.
  - Can run in parallel with implementation.
  - Prepares self-shot and derived validation images.
- [ ] **WS6 - Validation Evidence Run（验证证据运行与整理）**
  - Depends on WS4 and WS5.
  - Produces CSV logs, screenshots, validation table, and counterexample analysis.
- [ ] **WS7 - Contest Documentation Pack（比赛必交文档包）**
  - Depends on WS0 for drafts; final pass depends on WS6.
  - Completes README, algorithm notes, AI collaboration, constraints, and scoring alignment.
- [ ] **WS8 - Final ZIP Submission Package（最终 ZIP 提交包）**
  - Depends on WS6 and WS7.
  - Builds final APK and structured ZIP. GitHub is optional only after ZIP completion.

Detailed package briefs live under `docs/superpowers/task-packets/`.

## Phase 0 - Submission Frame

- [x] Confirm app platform and development environment.
- [x] Create final repository/package structure.
- [ ] Keep this task list updated as evidence of process.

## Phase 1 - Android MVP

- [ ] Create Android project.
- [ ] Implement image picker.
- [ ] Preview selected image.
- [x] Show image metadata: format, dimensions, file size.
- [x] Support JPEG, PNG, WebP.
- [x] Add large-image safe decode/downsampling.
- [x] Record original image size, analysis bitmap size, and whether downsampling occurred.

## Phase 2 - Quality Metrics

- [ ] Implement grayscale conversion.
- [x] Implement sharpness score.
- [x] Implement exposure score.
- [x] Implement contrast score.
- [x] Implement color cast score as the fourth quality dimension.
- [x] Implement weighted overall score.
- [x] Generate readable result explanation.
- [x] Record analysis time for each image.

## Phase 3 - Validation Evidence

- [ ] Prepare sample categories: clear, blur, overexposed, underexposed, noisy.
- [ ] Collect 2-3 images per category.
- [ ] Run app against all samples.
- [ ] Record sub-scores, overall score, and manual judgment.
- [ ] Add screenshots or logs.
- [ ] Add at least one counterexample and explain why it fails.

## Phase 4 - Contest Documents

- [ ] Complete README: goals, non-goals, completion criteria, run instructions.
- [ ] Complete 500-word constraints and decisions note.
- [ ] Complete validation evidence document.
- [ ] Complete AI collaboration document.
- [ ] Complete algorithm notes.
- [ ] Complete counterexample analysis.
- [ ] Add HEIC compatibility discussion without making it a minimum supported format.

## Phase 5 - Final Package

- [ ] Build APK.
- [ ] Verify install and run on real device or emulator.
- [ ] Check package naming rule.
- [ ] Ensure all required evidence is included.
- [ ] Create structured ZIP package as the primary submission.
- [ ] Publish to GitHub only if time remains after ZIP package completion.
- [ ] Final self-review against judging rubric.
