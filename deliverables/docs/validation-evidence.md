# Validation Evidence

## Evidence Status

This WS6 pass uses the current `samples/` folder from WS5 commit `bbfa008`.
The available sample set contains Wikimedia Commons images and format copies
derived from those images. These are documented external comparison samples, not
Redmi K70E self-shot images.

Current evidence produced:

- `logs/analysis_log.csv`: 21-row structured validation log for every current
  JPEG, PNG, and WebP sample.
- `screenshots/README.md`: screenshot inventory and pending Redmi K70E capture
  list.
- `screenshots/emulator_app_start.png`: Android Emulator App launch screen.
- `screenshots/emulator_clear_butterfly_jpeg_result.png`: real App UI result
  for `samples/clear/clear_butterfly_01.jpg`.
- `screenshots/emulator_blur_out_of_focus_jpeg_result.png`: real App UI result
  for `samples/blur/blur_out_of_focus_03.jpg`.
- `screenshots/emulator_format_clear_png_result.png`: real App UI result for
  `samples/formats/format_clear_png_01.png`.
- `screenshots/emulator_format_clear_webp_result.png`: real App UI result for
  `samples/formats/format_clear_webp_01.webp`.

The screenshots above were captured from Android Emulator `Medium_Phone`,
Android API 36.1. They are useful App-run evidence, but they are not Redmi K70E
true-device evidence.

Evidence still pending:

- Redmi K70E self-shot originals and derived samples.
- Redmi K70E real-device App screenshots.
- Redmi K70E manual App timing measurements.

After the Sharpness-Calibrate pass, score columns in `logs/analysis_log.csv`
were recalculated offline with the current scoring formulas. Existing emulator
screenshots remain useful UI-flow evidence, but they are pre-calibration
screenshots and may show older score values. The offline `analysis_ms` values
are not Android device timings.

## Sharpness Calibration Update

The original sharpness score used only global Laplacian variance. Validation
showed that clear natural images could be under-scored when the sharp subject
occupied only part of the frame, while texture/noise and high-frequency
background detail could over-score blur/noise samples. The current score
combines focused local Laplacian, global Laplacian, and Tenengrad with weights
60% / 25% / 15%.

Key recalculated score changes:

| Sample | Manual judgment | Sharpness before | Sharpness after | Overall before | Overall after | Interpretation |
|---|---|---:|---:|---:|---:|---|
| `clear/clear_butterfly_01.jpg` | Clear subject | 36 | 51 | 60 | 65 | Improved; no longer trapped in the 30-40 sharpness band. |
| `clear/clear_beetle_spider_02.jpg` | Clear macro/detail | 38 | 67 | 65 | 75 | Improved; local detail is now recognized. |
| `clear/clear_butterfly_03.jpg` | Clear focused subject | 53 | 76 | 71 | 79 | Improved; focused regions lift sharpness. |
| `blur/blur_out_of_focus_03.jpg` | Out-of-focus | 0 | 2 | 42 | 43 | Still low, as desired. |
| `blur/blur_subway_motion_01.jpg` | Motion blur | 80 | 90 | 87 | 90 | Still a counterexample; station texture/detail remains high. |
| `noisy/noisy_nind_keyboard_03.jpg` | High-ISO noise | 80 | 88 | 82 | 85 | Still a counterexample; noise/texture lifts edge metrics. |

## Sample Coverage

| Category | Current files | Source | Emulator App evidence | Redmi K70E evidence |
|---|---:|---|---|---|
| Clear | 3 | Wikimedia external samples | 1 captured | Pending |
| Blur | 3 | Wikimedia external samples | 1 captured | Pending |
| Overexposed | 3 | Wikimedia external samples | Pending | Pending |
| Underexposed | 3 | Wikimedia external samples | Pending | Pending |
| Noisy | 3 | Wikimedia external samples | Pending | Pending |
| Counterexamples | 3 | Wikimedia external samples | Pending | Pending |
| Formats | 3 | Wikimedia-derived JPEG/PNG/WebP copies | PNG and WebP captured | Pending |

Full source, author, license, preparation, manual judgment, and expected App
behavior are recorded in `samples/SOURCES.md`.

## Emulator App Run Summary

| Sample | Source | Manual judgment | Format | Overall | Match | Screenshot |
|---|---|---|---|---:|---|---|
| `clear/clear_butterfly_01.jpg` | Wikimedia external | Clear, sharp subject, normal exposure | JPEG | 65 | Yes | `screenshots/emulator_clear_butterfly_jpeg_result.png` |
| `blur/blur_out_of_focus_03.jpg` | Wikimedia external | Out-of-focus image | JPEG | 43 | Yes | `screenshots/emulator_blur_out_of_focus_jpeg_result.png` |
| `formats/format_clear_png_01.png` | Wikimedia-derived | PNG format validation copy | PNG | 65 | Yes | `screenshots/emulator_format_clear_png_result.png` |
| `formats/format_clear_webp_01.webp` | Wikimedia-derived | WebP format validation copy | WebP | 65 | Yes | `screenshots/emulator_format_clear_webp_result.png` |

The screenshots in this table were captured before the Sharpness-Calibrate
formula update. The updated overall values above come from offline recalculation
against the same sample files and should be recaptured in the app if exact UI
screenshots are needed for the final package.

The emulator run also verified the fixed Photo Picker flow after `content://`
Photo Picker URIs initially failed to decode on API 36.1. The App now loads the
selected JPEG, PNG, and WebP samples and displays format, original dimensions,
analysis dimensions, file size, downsampling status, score breakdown, overall
score, analysis time, and diagnosis.

## Full Validation Log Summary

| Sample | Source | Manual judgment | Overall | Match | Notes |
|---|---|---|---:|---|---|
| `blur/blur_out_of_focus_03.jpg` | Wikimedia external | Out-of-focus image | 43 | Yes | Recalculated sharpness remains very low. |
| `blur/blur_subway_motion_01.jpg` | Wikimedia external | Motion blur from moving train | 90 | No | Counterexample remains: high-frequency station detail keeps score high. |
| `blur/blur_tanoura_motion_02.jpg` | Wikimedia external | Intentional motion blur | 37 | Yes | Overall remains low for visible blur. |
| `clear/clear_beetle_spider_02.jpg` | Wikimedia external | Clear macro/detail image | 75 | Yes | Improved after local-block sharpness. |
| `clear/clear_butterfly_01.jpg` | Wikimedia external | Clear, sharp subject, normal exposure | 65 | Yes | Improved sharpness; color-cast warning remains. |
| `clear/clear_butterfly_03.jpg` | Wikimedia external | Clear focused subject | 79 | Yes | Improved after local-block sharpness. |
| `counterexamples/counterexample_bokeh_01.jpg` | Wikimedia external | Intentional night bokeh, human-acceptable | 25 | No | Intended failure: artistic blur/darkness is still treated as low quality. |
| `counterexamples/counterexample_colored_lights_02.jpg` | Wikimedia external | Colored night lighting, human-acceptable | 47 | No | Intended failure: night lighting and color bias remain penalized. |
| `counterexamples/counterexample_sunset_03.jpg` | Wikimedia external | Warm sunset light, human-acceptable | 85 | Partial | Overall is high, but warm color can still be described as cast. |
| `formats/format_clear_jpeg_01.jpg` | Wikimedia-derived | JPEG format validation copy | 65 | Pending true-device confirmation | Offline replay ran; representative JPEG UI evidence should be recaptured if needed. |
| `formats/format_clear_png_01.png` | Wikimedia-derived | PNG format validation copy | 65 | Yes | Format load evidence exists; score is recalculated offline. |
| `formats/format_clear_webp_01.webp` | Wikimedia-derived | WebP format validation copy | 65 | Yes | Format load evidence exists; score is recalculated offline. |
| `noisy/noisy_hong_kong_night_01.jpg` | Wikimedia external | Night image with visible noise | 64 | Partial | Still mostly penalizes exposure, not noise. |
| `noisy/noisy_nind_keyboard_03.jpg` | Wikimedia external | High-ISO noise sample | 85 | No | Counterexample remains: noise/texture lifts local sharpness and Tenengrad. |
| `noisy/noisy_nind_stefantiek_02.jpg` | Wikimedia external | Natural Image Noise Dataset sample | 78 | Partial | Still only partly aligned because there is no dedicated noise metric. |
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

Representative screenshots still to capture on Redmi K70E:

- App launch screen.
- Clear sample result screen.
- Blur sample result screen.
- Overexposed sample result screen.
- PNG format sample result screen.
- WebP format sample result screen.
- Counterexample result screen.

## Interpretation

The validation run is useful because it shows both successful matches and
important limitations:

- The App can load and analyze Photo Picker selected JPEG, PNG, and WebP files
  on the Android API 36.1 emulator after the image-decoding fix.
- The recalibrated logic catches the out-of-focus blur sample with very low
  sharpness.
- The local-block sharpness score improves clear macro/nature samples that were
  previously stuck around 30-40 sharpness.
- The format samples confirm PNG and WebP decoding in the App UI.
- Sharpness is still vulnerable to high-frequency background detail and noise,
  even after adding local Laplacian and Tenengrad.
- Global exposure can miss some underexposed-looking scenes when the histogram
  does not cross the clipping thresholds.
- Color-cast scoring can penalize legitimate warm or strongly colored scenes.
- Noisy samples remain weak evidence because the App has no dedicated noise
  score; they should be discussed as a known limitation rather than a completed
  noise detector.

## Redmi K70E Follow-Up

When the Redmi K70E is available, run the installed App manually against the
representative samples, capture the screenshots listed in
`screenshots/README.md`, and update `logs/analysis_log.csv` or add a separate
device log with real Android analysis times. Do not relabel Wikimedia samples
or emulator screenshots as Redmi K70E samples.
