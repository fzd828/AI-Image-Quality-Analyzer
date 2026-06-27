## ADDED Requirements

### Requirement: Multi-dimensional image quality analysis
The app SHALL compute at least three explainable image quality dimensions for each selected image.

#### Scenario: Image analysis completes
- **WHEN** the user selects a supported image and starts analysis
- **THEN** the app outputs sub-scores for at least sharpness, exposure, and contrast

### Requirement: Sharpness and blur score
The app SHALL estimate image sharpness or blur using an explainable traditional computer vision metric such as Laplacian variance or gradient statistics.

#### Scenario: Blurred image is analyzed
- **WHEN** the app analyzes an intentionally blurred sample image
- **THEN** the sharpness score is lower than the score for a comparable clear sample

### Requirement: Exposure score
The app SHALL estimate exposure quality using luminance statistics such as histogram distribution, underexposed pixel ratio, and overexposed pixel ratio.

#### Scenario: Overexposed image is analyzed
- **WHEN** the app analyzes an image with large clipped bright regions
- **THEN** the exposure result identifies an overexposure risk

#### Scenario: Underexposed image is analyzed
- **WHEN** the app analyzes an image with large clipped dark regions
- **THEN** the exposure result identifies an underexposure risk

### Requirement: Contrast score
The app SHALL estimate contrast using a grayscale contrast metric such as standard deviation or RMS contrast.

#### Scenario: Low contrast image is analyzed
- **WHEN** the app analyzes a low-contrast image
- **THEN** the contrast score is lower than the score for a comparable normal-contrast image

### Requirement: Additional quality dimension
The app SHALL include at least one additional quality dimension such as color cast or noise estimation.

#### Scenario: Additional dimension is displayed
- **WHEN** analysis completes
- **THEN** the app displays the additional dimension score together with its explanation

### Requirement: Overall score and readable diagnosis
The app SHALL produce a 0-100 overall quality score and a readable diagnosis based on the sub-scores.

#### Scenario: Overall result is shown
- **WHEN** analysis completes successfully
- **THEN** the app displays an overall score, sub-scores, and a short diagnosis sentence

### Requirement: Algorithm documentation
The submission MUST explain each scoring metric, its parameters, and its limitations.

#### Scenario: Reviewer reads algorithm notes
- **WHEN** a reviewer opens the algorithm documentation
- **THEN** the reviewer can identify the source idea, parameter meaning, and known limitation of each metric

