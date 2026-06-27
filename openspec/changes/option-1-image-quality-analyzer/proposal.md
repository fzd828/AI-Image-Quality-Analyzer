# Change Proposal: Option 1 Image Quality Analyzer

## Why

The contest rewards practical AI-enabled engineering work, but the judging rules emphasize process and evidence as much as final features. Option 1 is suitable because traditional computer vision metrics are explainable, testable, fast enough for mobile, and easier to validate than an API-heavy generative app.

## What

Create an Android image quality analyzer that:

- Selects local images from file picker or album.
- Supports at least JPEG, PNG, and WebP.
- Previews the selected image with basic metadata.
- Computes multiple image quality scores.
- Shows an overall score and readable diagnosis.
- Exports or documents validation evidence.

## Quality Dimensions

Initial metric set:

- Sharpness / blur: Laplacian variance or gradient statistics.
- Exposure: luminance histogram, overexposed ratio, underexposed ratio.
- Contrast: grayscale standard deviation or RMS contrast.
- Color cast: RGB channel balance or Lab/HSV channel statistics.

Optional extension:

- Noise estimate on flat regions.
- HEIC compatibility discussion or partial support.
- Processing-time and memory profiling.

## Success Criteria

- Runs on a real Android device.
- Loads at least three formats without OOM crashes.
- Can analyze a normal image in an acceptable time, target <= 1 second for typical images.
- Handles large images by safe downsampling.
- Produces overall score, sub-scores, and readable explanation.
- Includes 2-3 samples each for clear, blurred, overexposed, underexposed, and noisy images.
- Includes at least one counterexample where the score is clearly wrong, with analysis.

## Risks

- Absolute thresholds may not generalize across resolution, device, or scene type.
- Night scenes, artistic filters, shallow depth of field, and high-key images may be misjudged.
- Very large images may cause memory pressure without sampling.
- Image format support may vary by Android version, especially HEIC.

