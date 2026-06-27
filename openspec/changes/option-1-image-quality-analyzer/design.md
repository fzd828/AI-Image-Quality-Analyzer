# Design: Image Quality Analyzer

## Architecture

Use a small layered design:

- UI layer: image picker, preview, analysis result, sample evidence screen if time allows.
- Decode layer: load image metadata and bitmap safely with downsampling.
- Analysis layer: compute metrics on a normalized working bitmap.
- Scoring layer: map raw metrics into 0-100 sub-scores and overall score.
- Explanation layer: convert metric ranges into human-readable diagnosis.

## Platform Decision

Use Android native development with Kotlin and Jetpack Compose.

Rationale:

- It directly matches the contest expectation of a mobile app and APK delivery.
- It makes real-device validation, image picker behavior, large-image decoding, and memory constraints easier to demonstrate.
- Jetpack Compose keeps UI implementation efficient while leaving enough time for validation evidence and documentation.
- Native Android image APIs allow JPEG, PNG, and WebP support to be explained clearly.

## Image Decode Strategy

For stability, decode a display bitmap for preview and a normalized analysis bitmap for metrics.

Suggested analysis size:

- Long edge around 1024-1600 px.
- Preserve aspect ratio.
- Avoid analyzing full 12MP bitmap unless measuring performance.

Reason:

- Reduces OOM risk.
- Makes threshold behavior more stable across images.
- Keeps analysis latency acceptable.

## Metrics

### Sharpness

Use Laplacian variance on grayscale image.

Interpretation:

- Lower variance often means blur.
- Higher variance often means sharper edges.

Limitations:

- Textures, noise, and compression artifacts can increase variance.
- Smooth but correctly focused images may score lower.
- Thresholds depend on resize policy.

### Exposure

Use luminance histogram.

Signals:

- Mean luminance.
- Ratio of pixels near 0: underexposed risk.
- Ratio of pixels near 255: overexposed risk.

Limitations:

- Night scenes and high-key photos can be intentionally dark or bright.
- Local exposure quality is not captured by one global histogram.

### Contrast

Use grayscale standard deviation or RMS contrast.

Limitations:

- Low-contrast style photos can be intentional.
- High contrast does not always mean better quality.

### Color Cast

Use RGB channel imbalance after excluding extreme dark/bright pixels.

Limitations:

- Warm lighting, sunset, neon scenes, or filters may be intentionally color-biased.
- Without scene understanding, white balance judgment is approximate.

## Overall Score

Initial weights:

- Sharpness: 35%
- Exposure: 30%
- Contrast: 20%
- Color cast or noise: 15%

The weights are intentionally simple and explainable. They can be adjusted after validation against sample images.

## Selected Extensions

The project will include only low-risk extensions that directly strengthen scoring evidence:

- Analysis time measurement for each selected image.
- Decode and downsampling log showing original size and analysis size.
- A fourth quality dimension: color cast estimation.
- HEIC compatibility discussion in documentation, without making HEIC a minimum supported format.

The project will not include deep learning model deployment, complex charts, or JSON export in the core scope.

## AI Collaboration Plan

Use AI for:

- Generating candidate metrics and formulas.
- Explaining algorithm tradeoffs.
- Drafting README and validation tables.
- Suggesting counterexamples.

Human responsibilities:

- Select final scope.
- Run real-device tests.
- Validate AI-generated algorithm assumptions.
- Record wrong AI suggestions and corrections.
- Decide final thresholds based on observed data.

## Validation Sample Strategy

Use Redmi K70E self-shot photos as the primary source of final validation samples.

Plan:

- Capture 5-6 original photos covering normal clear scenes, text/details, dark scenes, bright scenes, colorful scenes, and at least one likely counterexample.
- Derive controlled degraded samples from originals, including blur, overexposure, underexposure, and noise.
- Export representative samples as JPEG, PNG, and WebP to validate required format support.
- Use a small number of natural counterexamples such as night scenes, sunset, shallow depth of field, or filter-like lighting to show algorithm limitations.

Rationale:

- Self-shot photos avoid copyright ambiguity.
- Controlled degradation makes before/after comparison more credible.
- Natural counterexamples directly support the contest requirement for critical thinking.

## Final Submission Form

The primary submission will be a structured ZIP package.

Expected package layout:

- `app/`: Android source project.
- `apk/`: installable APK.
- `docs/`: README, algorithm notes, constraints and decisions, validation evidence, counterexample analysis, AI collaboration notes, and scoring alignment.
- `samples/`: validation samples grouped by category.
- `screenshots/`: real-device screenshots.
- `logs/`: analysis logs such as CSV records for scores, timing, and downsampling.

GitHub publication is optional and will be considered only after the ZIP package is complete.
