# Counterexamples

The contest explicitly asks for at least one obvious scoring failure and analysis. This document should make that failure visible and useful.

## Candidate 1: Night Scene

Expected issue:

- Histogram exposure score may classify a normal night photo as underexposed.

Reason:

- The algorithm sees many dark pixels but does not understand scene intent.

What this proves:

- Global brightness is not enough to judge exposure quality.
- The system needs either scene classification or user context for better judgment.

## Candidate 2: Noisy But Sharp Image

Expected issue:

- Laplacian variance may give a high sharpness score because noise creates high-frequency response.

Reason:

- The algorithm cannot fully distinguish real edges from noise.

What this proves:

- Sharpness and noise should be considered together.

## Candidate 3: Shallow Depth Of Field Portrait

Expected issue:

- Background blur may reduce global sharpness score even though the subject is intentionally sharp.

Reason:

- The algorithm analyzes the whole image and does not locate the main subject.

What this proves:

- A professional quality metric may need subject detection or region-weighted analysis.

