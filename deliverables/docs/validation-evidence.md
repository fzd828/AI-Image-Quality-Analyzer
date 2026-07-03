# Validation Evidence

## Evidence Status

This validation pass uses the current `samples/` folder from WS5. The available
sample set contains documented Wikimedia Commons images and format copies
derived from those images. These are external comparison samples, not Redmi
K70E self-shot images.

Current evidence files:

- `logs/analysis_log.csv`: 21-row structured validation log for every current
  JPEG, PNG, and WebP sample.
- `screenshots/README.md`: screenshot inventory and pending recapture list.
- `screenshots/emulator_noisy_hong_kong_night_result.png`: current App UI
  result for `samples/noisy/noisy_hong_kong_night_01.jpg`.
- `screenshots/emulator_noisy_nind_keyboard_result.png`: current App UI result
  for `samples/noisy/noisy_nind_keyboard_03.jpg`.
- `screenshots/emulator_noisy_nind_stefantiek_result.png`: current App UI
  result for `samples/noisy/noisy_nind_stefantiek_02.jpg`.

The screenshots above were captured from Android Emulator `Medium_Phone`,
Android API 36.1, using the localized App UI and fine analysis mode 2560. They
are useful App-run evidence, but they are not Redmi K70E true-device evidence.

Older pre-calibration screenshots for launch, clear, blur, PNG, and WebP runs
were intentionally removed before this recapture pass. Those samples still have
structured score-log rows, but their reviewer-facing UI screenshots should be
recaptured from the current App before final packaging if needed.

Evidence still pending:

- Redmi K70E self-shot originals and derived samples.
- Redmi K70E real-device App screenshots.
- Redmi K70E manual App timing measurements.
- Current App UI screenshots for clear, blur, overexposed, underexposed,
  counterexample, PNG, and WebP representative samples.

## Sharpness Calibration Update

The original sharpness score used only global Laplacian variance. Validation
showed that clear natural images could be under-scored when the sharp subject
occupied only part of the frame, while texture/noise and high-frequency
background detail could over-score blur/noise samples. The current score uses
local-block Laplacian as the primary signal, keeps global Laplacian as a
secondary signal, and uses Tenengrad/Sobel as a low-weight auxiliary signal.

Key recalculated score changes:

| Sample | Manual judgment | Sharpness before | Sharpness after | Overall before | Overall after | Interpretation |
|---|---|---:|---:|---:|---:|---|
| `clear/clear_butterfly_01.jpg` | Clear subject | 36 | 51 | 60 | 65 | Improved; no longer trapped in the 30-40 sharpness band. |
| `clear/clear_beetle_spider_02.jpg` | Clear macro/detail | 38 | 67 | 65 | 75 | Improved; local detail is now recognized. |
| `clear/clear_butterfly_03.jpg` | Clear focused subject | 53 | 76 | 71 | 79 | Improved; focused regions lift sharpness. |
| `blur/blur_out_of_focus_03.jpg` | Out-of-focus | 0 | 2 | 42 | 43 | Still low, as desired. |
| `blur/blur_subway_motion_01.jpg` | Motion blur | 80 | 90 | 87 | 90 | Still a counterexample; station texture/detail remains high. |
| `noisy/noisy_nind_keyboard_03.jpg` | High-ISO noise | 80 | 96 in current fine-mode UI | 82 | 87 in current fine-mode UI | Still a counterexample; noise/texture lifts edge metrics. |

## Sample Coverage

| Category | Current files | Source | Current emulator App screenshots | Redmi K70E evidence |
|---|---:|---|---|---|
| Clear | 3 | Wikimedia external samples | Pending recapture | Pending |
| Blur | 3 | Wikimedia external samples | Pending recapture | Pending |
| Overexposed | 3 | Wikimedia external samples | Pending recapture | Pending |
| Underexposed | 3 | Wikimedia external samples | Pending recapture | Pending |
| Noisy | 3 | Wikimedia external samples | 3 captured | Pending |
| Counterexamples | 3 | Wikimedia external samples | Pending recapture | Pending |
| Formats | 3 | Wikimedia-derived JPEG/PNG/WebP copies | Pending recapture for current UI | Pending |

Full source, author, license, preparation, manual judgment, and expected App
behavior are recorded in `samples/SOURCES.md`.

## Current Emulator App Run Summary

| Sample | Source | Manual judgment | Format | Mode | Analysis size | Time | Overall | Match | Screenshot |
|---|---|---|---|---|---|---:|---:|---|---|
| `noisy/noisy_hong_kong_night_01.jpg` | Wikimedia external | Night image with visible noise | JPEG | Fine 2560 | 1920 x 1258 | 188 ms | 67 | Partial | `screenshots/emulator_noisy_hong_kong_night_result.png` |
| `noisy/noisy_nind_keyboard_03.jpg` | Wikimedia external | High-ISO noise sample | JPEG | Fine 2560 | 1920 x 1272 | 145 ms | 87 | No | `screenshots/emulator_noisy_nind_keyboard_result.png` |
| `noisy/noisy_nind_stefantiek_02.jpg` | Wikimedia external | Natural Image Noise Dataset sample | JPEG | Fine 2560 | 1358 x 2560 | 248 ms | 78 | Partial | `screenshots/emulator_noisy_nind_stefantiek_result.png` |

The noisy screenshots are especially useful because they show a limitation:
without a dedicated noise metric, high-frequency noise and texture can raise
sharpness and edge responses. The keyboard sample is therefore a clear
counterexample: the human label is noisy, but the App gives it 87/100.
Diagnosis Explanation V2 adds conservative texture/noise risk wording for this
kind of case, but it does not change the score, add an independent noise
metric, or make the existing screenshots new evidence for noise detection.

## Full Validation Log Summary

| Sample | Source | Manual judgment | Overall | Match | Notes |
|---|---|---|---:|---|---|
| `blur/blur_out_of_focus_03.jpg` | Wikimedia external | Out-of-focus image | 43 | Yes | Recalculated sharpness remains very low; current UI screenshot pending recapture. |
| `blur/blur_subway_motion_01.jpg` | Wikimedia external | Motion blur from moving train | 90 | No | Counterexample remains: high-frequency station detail keeps score high. |
| `blur/blur_tanoura_motion_02.jpg` | Wikimedia external | Intentional motion blur | 37 | Yes | Overall remains low for visible blur. |
| `clear/clear_beetle_spider_02.jpg` | Wikimedia external | Clear macro/detail image | 75 | Yes | Improved after local-block sharpness. |
| `clear/clear_butterfly_01.jpg` | Wikimedia external | Clear, sharp subject, normal exposure | 65 | Yes | Improved sharpness; color-cast warning remains; current UI screenshot pending recapture. |
| `clear/clear_butterfly_03.jpg` | Wikimedia external | Clear focused subject | 79 | Yes | Improved after local-block sharpness. |
| `counterexamples/counterexample_bokeh_01.jpg` | Wikimedia external | Intentional night bokeh, human-acceptable | 25 | No | Intended failure: artistic blur/darkness is still treated as low quality. |
| `counterexamples/counterexample_colored_lights_02.jpg` | Wikimedia external | Colored night lighting, human-acceptable | 47 | No | Intended failure: night lighting and color bias remain penalized. |
| `counterexamples/counterexample_sunset_03.jpg` | Wikimedia external | Warm sunset light, human-acceptable | 85 | Partial | Overall is high, but warm color can still be described as cast. |
| `formats/format_clear_jpeg_01.jpg` | Wikimedia-derived | JPEG format validation copy | 65 | Pending UI recapture | Offline replay ran; representative JPEG UI evidence should be recaptured if needed. |
| `formats/format_clear_png_01.png` | Wikimedia-derived | PNG format validation copy | 65 | Pending UI recapture | Current UI screenshot pending recapture. |
| `formats/format_clear_webp_01.webp` | Wikimedia-derived | WebP format validation copy | 65 | Pending UI recapture | Current UI screenshot pending recapture. |
| `noisy/noisy_hong_kong_night_01.jpg` | Wikimedia external | Night image with visible noise | 67 | Partial | Current emulator App UI screenshot captured; score mainly reflects exposure penalty, not noise detection. |
| `noisy/noisy_nind_keyboard_03.jpg` | Wikimedia external | High-ISO noise sample | 87 | No | Current emulator App UI screenshot captured; noise/texture still lifts sharpness and overall. |
| `noisy/noisy_nind_stefantiek_02.jpg` | Wikimedia external | Natural Image Noise Dataset sample | 78 | Partial | Current emulator App UI screenshot captured; no dedicated noise metric. |
| `overexposed/overexposed_photo_01.jpg` | Wikimedia external | Washed-out highlights | 50 | Yes | Exposure penalty keeps result weak. |
| `overexposed/overexposed_sky_02.jpg` | Wikimedia external | Overexposed sky | 81 | Partial | Strong edges and contrast still keep overall high. |
| `overexposed/overexposed_space_washed_03.jpg` | Wikimedia external | Washed-out overexposed space photograph | 40 | Yes | Exposure score drops to zero. |
| `underexposed/underexposed_pollen_03.jpg` | Wikimedia external | Dark low-exposure image | 34 | Yes | Exposure score drops to zero. |
| `underexposed/underexposed_space_02.jpg` | Wikimedia external | Underexposed space photograph | 39 | Yes | Overall is low, but exposure remains a metric limitation. |
| `underexposed/underexposed_sun_01.jpg` | Wikimedia external | Very dark frame | 30 | Yes | Exposure score drops to zero. |

For per-sample format, original size, analysis size, downsampling flag,
analysis time, sharpness, exposure, contrast, color-cast, overall score, match,
and detailed limitation notes, see `logs/analysis_log.csv`.

## Screenshot Evidence

Captured emulator screenshots are listed in `screenshots/README.md`.

Representative screenshots still to capture or recapture:

- Current emulator App launch screen.
- Current emulator clear sample result screen.
- Current emulator blur sample result screen.
- Current emulator PNG and WebP format result screens.
- Current emulator counterexample result screen.
- Redmi K70E App launch and representative result screens if the phone is
  available before final packaging.

## Interpretation

The validation run is useful because it shows both successful matches and
important limitations:

- The App can load and analyze Photo Picker or file-picker selected JPEG
  samples on Android API 36.1 emulator.
- Fine mode 2560 handles the noisy samples without OOM; the largest noisy
  sample is safely downsampled from 1618 x 3050 to 1358 x 2560.
- The recalibrated sharpness logic catches some blur cases, but it is still
  vulnerable to high-frequency texture and noise.
- Noisy samples remain weak evidence for a "noise detector" because the App has
  no dedicated noise score. They should be discussed as a known limitation.
- Global exposure can miss some underexposed-looking scenes when the histogram
  does not cross the clipping thresholds.
- Color-cast scoring can penalize legitimate warm or strongly colored scenes.

## Redmi K70E Follow-Up

When the Redmi K70E is available, run the installed App manually against the
representative samples, capture the screenshots listed in
`screenshots/README.md`, and update `logs/analysis_log.csv` or add a separate
device log with real Android analysis times. Do not relabel Wikimedia samples
or emulator screenshots as Redmi K70E samples.
