# Scoring Alignment

This document maps the final package to the contest judging signals.

## Passion - 25%

Evidence delivered:

- Android native app with local image picker, preview, metadata, scoring, diagnosis, and analysis modes.
- More than the minimum three dimensions: sharpness, exposure, contrast, and color cast / texture-noise risk.
- Final validation log with 32 structured records: 22 emulator records and 10 Redmi K70E representative true-device records.
- Format coverage for JPEG, PNG, and WebP, plus large-image downsampling evidence.

Message to reviewer:

The work goes beyond a minimal demo by connecting app behavior, sample evidence, device screenshots, algorithm notes, and known failure cases.

## Critical Thinking - 20%

Evidence delivered:

- `deliverables/docs/counterexamples.md` keeps two final counterexamples: overexposed train sky and noisy Hong Kong night scene.
- `deliverables/docs/ai-collaboration.md` records AI suggestions that were rejected or corrected.
- `deliverables/docs/algorithm-notes.md` explains why local-block sharpness improved the first global-Laplacian approach but still fails on noise and dense texture.

Message to reviewer:

The submission does not blindly trust AI or the score. It documents where the app is useful, where it is wrong, and why those failures happen.

## Questioning Ability - 20%

Evidence delivered:

- AI collaboration notes record the question path from broad assignment framing to concrete validation decisions.
- OpenSpec artifacts split the project into requirements, design, tasks, and named work packages.
- README and validation evidence answer the reviewer-facing questions: what was built, what was not built, why, and how it was verified.

Message to reviewer:

The broad contest topic was decomposed into answerable engineering questions: mobile format support, safe decoding, explainable metrics, scoring limits, evidence, and reproducibility.

## Integration Ability - 20%

Evidence delivered:

- `app/` source, `samples/`, `logs/final_validation_log.csv`, `screenshots/`, and `deliverables/docs/` refer to the same final evidence set.
- README links the required documents and gives a traceable code-to-evidence path.
- Validation documents explicitly separate Android Emulator evidence, Redmi K70E true-device evidence, external samples, and phone-generated JPEG copies.

Message to reviewer:

The final package integrates AI-assisted work, Android implementation, traditional CV metrics, screenshots, logs, and human judgment into one coherent deliverable.

## Learning Ability - 15%

Evidence delivered:

- Algorithm notes explain Laplacian variance, focused local-block sharpness, Tenengrad, exposure histogram logic, contrast, color cast, and HEIC compatibility boundaries.
- Validation evidence shows threshold and diagnosis behavior after observing real samples.
- Counterexamples show learning from failure rather than hiding weak cases.

Message to reviewer:

The project demonstrates fast learning of unfamiliar image-quality concepts and turns that learning into a runnable, documented Android submission.
