## ADDED Requirements

### Requirement: Comparison sample set
The submission MUST include or document a comparison sample set covering clear, blurred, overexposed, underexposed, and noisy images.

#### Scenario: Reviewer checks sample coverage
- **WHEN** a reviewer opens the validation evidence document
- **THEN** the document lists 2-3 samples for each required category or explains how the samples were prepared

### Requirement: Manual judgment comparison
The submission SHALL compare app scores with human judgment for the validation samples.

#### Scenario: Reviewer checks validation table
- **WHEN** a reviewer opens the validation evidence document
- **THEN** the document shows manual judgment, sub-scores, overall score, and match notes for each sample

### Requirement: Counterexample analysis
The submission MUST include at least one obvious scoring failure and explain why it fails.

#### Scenario: Reviewer checks counterexample
- **WHEN** a reviewer opens the counterexample document
- **THEN** the document describes the sample, expected human judgment, app result, failure reason, and possible improvement

### Requirement: Runtime evidence
The submission SHALL include evidence that the app can run and analyze images, such as screenshots, logs, or a reproducible screen sequence.

#### Scenario: Reviewer checks runtime evidence
- **WHEN** a reviewer opens the validation evidence document
- **THEN** the document includes screenshots or logs showing image loading, analysis output, and analysis time where available

### Requirement: Reproducible package structure
The final package MUST include source code or a reproducible repository layout plus README instructions.

#### Scenario: Reviewer attempts to understand the package
- **WHEN** a reviewer opens the package root
- **THEN** the reviewer can find README, source code or run instructions, validation evidence, algorithm notes, AI collaboration notes, and counterexample analysis

