# Scoring Alignment

This document maps the final work to the contest judging rules.

## Passion - 25%

Evidence to prepare:

- More than the minimum three metrics if time allows.
- Redmi K70E self-shot sample set across multiple failure modes.
- Notes about image format differences.
- Records of threshold tuning and performance checks.

Target message:

I did not just finish a required app. I investigated how image quality scoring works, tested it, and understood where it breaks.

## Critical Thinking - 20%

Evidence to prepare:

- At least one AI suggestion that was wrong or incomplete.
- At least one algorithmic counterexample.
- A repeatable way to judge whether the score is reasonable.

Examples:

- Local/global Laplacian and Tenengrad can still confuse noise or dense texture
  with sharpness.
- Global brightness can misjudge night scenes as underexposed.
- Color-balance checks can misjudge sunset photos as color cast.

Target message:

I do not blindly accept AI or algorithm output. I test it, challenge it, and document limitations.

## Questioning Ability - 20%

Evidence to prepare:

- A short question iteration log in `ai-collaboration.md`.
- Show how the problem was decomposed:
  - What metrics are feasible on mobile?
  - How do we avoid OOM on large images?
  - Which thresholds are stable?
  - What counterexamples should be tested?

Target message:

I can turn a broad task into structured questions and use AI to push beyond one-shot answers.

## Integration Ability - 20%

Evidence to prepare:

- App, algorithms, validation table, screenshots, and documents agree with each other.
- The overall score formula connects sub-scores into one explainable result.
- README links to validation and limitations.

Target message:

I can combine AI output, engineering constraints, test data, and my own judgment into a coherent deliverable.

## Learning Ability - 15%

Evidence to prepare:

- Notes on newly learned topics:
  - Laplacian variance, local-block sharpness, and Tenengrad edge energy.
  - Histogram-based exposure analysis.
  - Android image decoding and downsampling.
  - Format differences among JPEG, PNG, WebP.

Target message:

I can learn unfamiliar concepts quickly and turn them into a working demo.
