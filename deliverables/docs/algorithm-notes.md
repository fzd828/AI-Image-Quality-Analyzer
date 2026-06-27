# Algorithm Notes

## Sharpness / Blur

Candidate method: Laplacian variance on grayscale image.

Raw signal:

- High variance: more edge/detail response.
- Low variance: likely blur.

Parameters to document:

- Analysis bitmap size.
- Laplacian kernel.
- Normalization range.

Known limitations:

- Noise and compression artifacts can look like sharp details.
- Smooth scenes may have low edge response even when focused.
- Thresholds change if the analysis size changes.

## Exposure

Candidate method: luminance histogram.

Raw signals:

- Mean luminance.
- Underexposed pixel ratio.
- Overexposed pixel ratio.

Known limitations:

- Night scenes may be intentionally dark.
- Snow, sky, or high-key images may be intentionally bright.
- Global histogram ignores local subject exposure.

## Contrast

Candidate method: grayscale standard deviation or RMS contrast.

Known limitations:

- Low contrast may be artistic style.
- High contrast may hide detail in shadows or highlights.

## Color Cast

Candidate method: RGB channel imbalance after excluding extremely dark or bright pixels.

Known limitations:

- Warm light, sunset, neon, and filters may be intentional.
- Without scene semantics, the method can only estimate.

## Overall Score

Initial score formula:

```text
overall = 0.35 * sharpness
        + 0.30 * exposure
        + 0.20 * contrast
        + 0.15 * color_or_noise
```

The formula should be adjusted only after validation evidence shows a clear reason.

## Performance And Large Images

The app records analysis time for each image.

The app also records:

- Original image dimensions.
- Analysis bitmap dimensions.
- Whether downsampling was used.

This evidence is used to support the contest requirement that analysis time should be acceptable and large images should not cause OOM crashes.

## HEIC Compatibility

HEIC is discussed as an extension rather than a minimum supported format.

Reason:

- Android HEIC support depends on OS version, device codec availability, and decoder API behavior.
- The contest requires at least three common formats, so JPEG, PNG, and WebP are the core commitment.
- HEIC can be tested as a stretch goal if the device and Android API support it reliably.
