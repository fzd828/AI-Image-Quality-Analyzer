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

- First batch now: WS1, WS3, WS5, WS7.
- Second batch after WS1 passes review: WS2.
- Third batch after WS2 and WS3 complete: WS4.
- Fourth batch after WS4 and WS5 complete: WS6.
- Final batch after WS6 and WS7 complete: WS8.

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
- WS1: accepted and committed as the picker/preview baseline.
- WS2: completed locally. `ImageLoader` now provides metadata, required format detection, preview decode, and safe downsampled analysis bitmap output. Unit tests and debug build have passed.
- WS3: completed separately as the raw quality metrics engine.
- WS4-WS8: not started.

## Conversation Count Recommendation

If each work package gets its own conversation, create 8 new conversations total because WS0 is already done.

Do not create all 8 immediately. Create them in dependency order:

1. Now: create 4 conversations for WS1, WS3, WS5, and WS7.
2. After WS1 is accepted: create WS2.
3. After WS2 and WS3 are accepted: create WS4.
4. After WS4 and WS5 are accepted: create WS6.
5. After WS6 and WS7 are accepted: create WS8.

If you want fewer conversations, merge these pairs:

- WS1 + WS2 can be one App input pipeline conversation.
- WS6 + WS8 can be one final evidence and packaging conversation.

That reduces the total to 6 conversations, but the 8-conversation version is cleaner and easier to review.

## Recommended Conversation Prompts

Use prompts like:

```text
请执行 WS2 图片解码、元数据与大图安全。先读 AGENTS.md 和 docs/superpowers/task-packets/WS2-decode-metadata-large-image-safety.md，只做这个工作包。
```

```text
请执行 WS7 比赛必交文档包。先读 AGENTS.md 和 docs/superpowers/task-packets/WS7-contest-documentation-pack.md，只做文档，不改 app 代码。
```
