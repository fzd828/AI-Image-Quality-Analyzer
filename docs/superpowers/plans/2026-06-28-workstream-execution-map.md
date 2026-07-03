# Image Quality Analyzer Workstream Execution Map

> **For future conversations:** Pick one work package from `docs/superpowers/task-packets/`, read its packet first, then execute only that package. Do not silently expand scope.

**Goal:** Split the Option 1 image quality analyzer into named work packages that can be executed in separate Codex conversations while preserving OpenSpec acceptance criteria and Superpowers process discipline.

**Primary OpenSpec Change:** `option-1-image-quality-analyzer`

**Primary Deadline:** 2026-07-07 18:00

## Dependency Graph

```text
WS0 Project Baseline
  -> WS1 Picker Preview
      -> WS2 Decode Metadata
          -> WS4 Scoring UI

WS0 Project Baseline
  -> WS3 Quality Metrics
      -> WS4 Scoring UI

WS4 Scoring UI
  -> WS6 Validation Evidence
      -> WS8 Final Package

WS5 Sample Set can start after WS0
WS7 Contest Documents can start after WS0 and should be finalized after WS6
```

## Work Packages

You do not need to create all conversations at once.

Recommended batching:

- Completed code batch: WS1, WS2, WS3, and WS4.
- Completed evidence batch: WS5 sample set and WS6 emulator validation evidence.
- Current batch: WS7 documentation finalization can proceed.
- Final batch after WS7 completes: WS8 final ZIP package.

| ID | Name | Can Start When | Main Output |
|---|---|---|---|
| WS0 | Project Baseline And Build Verification（项目基线与构建验证） | Now; mostly complete | Buildable Android skeleton, baseline notes |
| WS1 | Picker Preview Flow（图片选择与预览流程） | WS0 complete | Select local image and preview it |
| WS2 | Decode Metadata And Large Image Safety（图片解码、元数据与大图安全） | WS1 complete | `ImageLoader`, metadata, downsampled analysis bitmap |
| WS3 | Quality Metrics Engine（画质指标计算引擎） | WS0 complete, can run in parallel with WS2 if interfaces are respected | raw image quality metrics and unit tests |
| WS4 | Scoring And Result UI（评分规则与结果展示界面） | WS2 and WS3 complete | 0-100 scores, explanation, analysis time in UI |
| WS5 | Redmi K70E Sample Set（红米 K70E 样本图片集） | WS0 complete | self-shot and derived samples |
| WS6 | Validation Evidence Run（验证证据运行与整理） | WS4 and WS5 complete | CSV log, screenshots, validation docs, counterexample |
| WS7 | Contest Documentation Pack（比赛必交文档包） | WS0 complete, final pass after WS6 | README and required docs |
| WS8 | ZIP Submission Package（最终 ZIP 提交包） | WS6 and WS7 complete | final structured ZIP, APK, self-review |

## Coordination Rules

- Only one package should edit `ImageAnalyzerScreen.kt` at a time.
- WS3 may create analysis code and tests without touching UI.
- WS5 may work only under `samples/`.
- WS7 may work only under `deliverables/` until final integration.
- WS8 owns `apk/`, final ZIP structure, and final checklist.
- If a package discovers a design change, update OpenSpec first instead of improvising.

## Current Workstream State

- WS0: mostly complete; Task 1 reviewer approved. Real-device run still pending until Redmi K70E is connected.
- WS1: completed and committed as `cad805e feat: add image picker preview flow`.
- WS2: completed and committed as `7e41851 feat: add safe image loading metadata`.
- WS3: completed and committed as `a028db2 feat: add image quality metrics engine`; memory optimization committed as `f2ba977 perf: stream laplacian variance calculation`.
- WS4: completed and committed as `d3b2d57 feat: add scoring result ui`; scroll/inset fix committed as `ad853b3 fix: add result screen scroll insets`.
- WS5: completed and committed as `bbfa008 docs: prepare redmi k70e sample set`. Current samples are documented Wikimedia/open-license comparison samples; Redmi K70E self-shot samples remain optional follow-up evidence.
- WS6: completed and committed through `935603f docs: add validation evidence run`, `726333b fix: support photo picker image decoding`, and `8e57d7c docs: add emulator validation evidence`. The current screenshot folder now keeps three current localized Android Emulator noisy-sample result screenshots, and `logs/analysis_log.csv` records all 21 samples. Older pre-calibration emulator screenshots were removed and should be recaptured only if needed for final packaging. Redmi K70E true-device screenshots and timing remain pending follow-up evidence.
- WS7: should now finalize reviewer-facing documentation using the WS5/WS6 evidence and clearly preserve the emulator-vs-Redmi evidence boundary.
- WS8: blocked until WS7 completes, then owns final APK copy, structured ZIP, and final checklist.

## Conversation Count Recommendation

WS1-WS4 are complete and should not keep producing code.

From the current state, create or continue conversations in this order:

1. Now: continue or create WS7 for final documentation, staying out of app code unless a broken reference must be corrected.
2. After WS7 is accepted: create WS8 for final APK and ZIP packaging.
3. Optional before WS8 final self-review: add Redmi K70E true-device screenshots and timing if the phone validation is available.

If you want fewer conversations, merge these pairs:

- WS1 + WS2 can be one App input pipeline conversation.
- WS6 + WS8 can be one final evidence and packaging conversation.

That reduces the total to 6 conversations, but the 8-conversation version is cleaner and easier to review.

## Recommended Conversation Prompts

Use prompts like:

```text
请执行 WS7 比赛必交文档包。先读 AGENTS.md、docs/superpowers/task-packets/WS7-contest-documentation-pack.md、docs/superpowers/plans/2026-06-28-workstream-execution-map.md、deliverables/docs/、logs/analysis_log.csv、samples/SOURCES.md 和 screenshots/README.md。只做文档，不改 app 代码。必须明确区分 Android Emulator 证据、Redmi K70E 待补证据、Wikimedia 外部样本和自拍样本。
```

```text
请执行 WS8 最终 ZIP 提交包。只有在 WS7 完成并提交后再开始。先读 AGENTS.md、docs/superpowers/task-packets/WS8-final-zip-submission.md 和 workstream map。负责最终 APK、结构化 ZIP 和最终自查，不要改 app 业务逻辑。
```
