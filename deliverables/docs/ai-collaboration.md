# AI Collaboration

## Collaboration Principle

AI is used as a research and drafting assistant. Final decisions, validation, threshold selection, and correctness judgment are human responsibilities.

## Planned AI Usage

| Stage | AI Contribution | Human Verification |
|---|---|---|
| Topic analysis | Summarized contest requirements and judging rubric | Checked against the PDF |
| Metric selection | Suggested sharpness, exposure, contrast, color cast, noise | Selected feasible mobile metrics |
| Algorithm drafting | Drafted formulas and implementation ideas | Tested with sample images |
| Documentation | Drafted README and evidence templates | Edited to match real results |
| Counterexamples | Suggested likely failure cases | Reproduced at least one failure |

## Question Iteration Log

To be filled during the project.

Recommended structure:

1. What does the contest actually reward?
2. Which metrics can be implemented quickly and explained clearly?
3. How can a large image be decoded without OOM?
4. How should raw metrics be normalized into 0-100 scores?
5. Which photos will break the scoring rules?
6. How should the final package prove that the result is reproducible?

## AI Mistakes Or Corrections

To be filled with real examples.

Candidate examples:

- AI may suggest fixed Laplacian thresholds without considering resize policy.
- AI may equate low brightness with poor exposure, which fails on night scenes.
- AI may recommend deep learning models, but this may not fit the time and deployment constraints.

