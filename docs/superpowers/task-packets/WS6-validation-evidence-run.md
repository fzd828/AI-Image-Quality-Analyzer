# WS6: Validation Evidence Run（验证证据运行与整理）

## Purpose

Run the finished app against samples and turn results into reviewer-facing evidence.

## Dependencies

- WS4 complete.
- WS5 sample set available.

## Read First

- `AGENTS.md`
- `openspec/changes/option-1-image-quality-analyzer/specs/validation-evidence-package/spec.md`
- `deliverables/docs/validation-evidence.md`
- `deliverables/docs/counterexamples.md`

## Allowed Write Scope

- `logs/analysis_log.csv`
- `screenshots/`
- `deliverables/docs/validation-evidence.md`
- `deliverables/docs/counterexamples.md`

## Required CSV Header

```csv
sample,format,manual_judgment,original_size,analysis_size,downsampled,analysis_ms,sharpness,exposure,contrast,color_cast,overall,match,notes
```

## Acceptance Criteria

- Validation table includes manual judgment and app output.
- At least one counterexample is documented with failure reason.
- Screenshots show app running on representative samples.
- Evidence includes analysis time and downsampling status where available.
