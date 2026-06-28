# WS5: Redmi K70E Sample Set（红米 K70E 样本图片集）

## Purpose

Prepare the validation image set for final evidence.

## Dependencies

- Can start after WS0.
- Does not require app implementation.

## Read First

- `AGENTS.md`
- `openspec/changes/option-1-image-quality-analyzer/specs/validation-evidence-package/spec.md`
- `deliverables/docs/validation-evidence.md`

## Allowed Write Scope

- `samples/originals/`
- `samples/clear/`
- `samples/blur/`
- `samples/overexposed/`
- `samples/underexposed/`
- `samples/noisy/`
- `samples/counterexamples/`
- `samples/formats/`
- Optional sample README under `samples/README.md`

## Capture Plan

Use Redmi K70E to capture:

- normal clear scene
- text/detail scene
- dark indoor or night scene
- bright window/sky/white wall scene
- colorful scene
- one natural counterexample such as night, sunset, shallow depth of field, or strong colored lighting

## Acceptance Criteria

- Each category has 2-3 samples or a clear note explaining pending captures.
- Sample source and transformations are documented.
- At least one JPEG, one PNG, and one WebP sample are available.
- No copyrighted external sample is used unless source/license is documented.
