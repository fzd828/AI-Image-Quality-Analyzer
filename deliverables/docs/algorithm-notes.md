# Algorithm Notes

## Sharpness / Blur

Final method: combine global Laplacian variance, focused local-block Laplacian variance, and Tenengrad edge energy on a downsampled grayscale analysis bitmap.

Sharpness-Calibrate upgrade:

- Keep the original global Laplacian variance as an overall whole-image
  sharpness signal.
- Add `focusedLaplacianVariance`: split the analysis bitmap into an 8 x 8
  grid, compute Laplacian variance per local block, then average the clearest
  top 25% blocks. This gives more weight to likely subject/detail regions and
  reduces the penalty from smooth background or shallow depth of field.
- Add `tenengrad`: Sobel gradient energy. It is useful as extra edge evidence,
  but noise and dense texture can raise it, so it is only a low-weight
  auxiliary signal.

Raw signal:

- High global Laplacian: more whole-frame edge/detail response.
- High focused Laplacian: at least some local regions contain clear detail.
- High Tenengrad: strong Sobel edge energy.
- Low values across all three signals: likely blur or low-detail content.

Parameters to document:

- Analysis bitmap size.
- Laplacian kernel.
- Focus grid: 8 x 8 blocks.
- Focused block selection: top 25% block variances.
- Sharpness score weights: focused Laplacian 60%, global Laplacian 25%,
  Tenengrad 15%.
- Normalization half-score values: focused Laplacian 150, global Laplacian 400,
  Tenengrad 4000.

Upgrade reason:

The first implementation used only global Laplacian variance. It was simple,
fast, and explainable, but validation showed two problems: natural photos and
shallow-depth-of-field images could be dragged down by smooth background areas,
while noise, dense texture, or high-frequency background detail could raise the
edge response. The upgraded score uses local-block Laplacian to focus on the
clearest regions, keeps global Laplacian for whole-frame stability, and adds
Tenengrad only as supporting edge-energy evidence.

Known limitations:

- Noise and compression artifacts can look like sharp details.
- Complex texture and high-ISO noise can still increase both local Laplacian
  and Tenengrad.
- Smooth scenes may have low edge response even when focused.
- Artistic blur, motion blur, and shallow depth of field still need human
  interpretation because the app has no subject or scene-intent model.
- Thresholds change if the analysis size changes.

## Exposure

Final method: luminance histogram and clipped-pixel ratios.

Raw signals:

- Mean luminance.
- Underexposed pixel ratio.
- Overexposed pixel ratio.

Known limitations:

- Night scenes may be intentionally dark.
- Snow, sky, or high-key images may be intentionally bright.
- Global histogram ignores local subject exposure.

## Contrast

Final method: grayscale contrast statistics, mainly standard deviation / RMS-style contrast.

Known limitations:

- Low contrast may be artistic style.
- High contrast may hide detail in shadows or highlights.

## Color Cast

Final method: RGB channel imbalance after excluding extremely dark or bright pixels.

Known limitations:

- Warm light, sunset, neon, and filters may be intentional.
- Without scene semantics, the method can only estimate.

## Overall Score

Final score formula:

```text
overall = 0.35 * sharpness
        + 0.30 * exposure
        + 0.20 * contrast
        + 0.15 * color_or_noise
```

Validation shows this formula is explainable and stable for the required demo, but it can overrate images where one serious problem is diluted by other high sub-scores. The overexposed train-sky counterexample documents that limitation.

## Diagnosis Explanation Layer

Diagnosis Explanation V2 changes only the result text layer. It can now list up
to three weak dimensions in severity order, distinguish underexposure from
overexposure, and add a conservative texture/noise risk sentence when very high
edge response appears together with dark or low-exposure conditions.

当前诊断层增加了保守的噪声/纹理风险提示，但没有新增独立噪声检测指标，因此 noisy 样本仍属于算法局限展示。

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
