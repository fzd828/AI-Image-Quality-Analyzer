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

Rows in `logs/analysis_log.csv` that reference emulator screenshots use actual
App UI scores and timings from the emulator run. Rows without emulator
screenshots remain local offline replays of the same scoring formulas against
the sample files. The offline `analysis_ms` values are not Android device
timings.

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
| `clear/clear_butterfly_01.jpg` | Wikimedia external | Clear, sharp subject, normal exposure | JPEG | 60 | Partial | `screenshots/emulator_clear_butterfly_jpeg_result.png` |
| `blur/blur_out_of_focus_03.jpg` | Wikimedia external | Out-of-focus image | JPEG | 42 | Yes | `screenshots/emulator_blur_out_of_focus_jpeg_result.png` |
| `formats/format_clear_png_01.png` | Wikimedia-derived | PNG format validation copy | PNG | 60 | Yes | `screenshots/emulator_format_clear_png_result.png` |
| `formats/format_clear_webp_01.webp` | Wikimedia-derived | WebP format validation copy | WebP | 59 | Yes | `screenshots/emulator_format_clear_webp_result.png` |

The emulator run also verified the fixed Photo Picker flow after `content://`
Photo Picker URIs initially failed to decode on API 36.1. The App now loads the
selected JPEG, PNG, and WebP samples and displays format, original dimensions,
analysis dimensions, file size, downsampling status, score breakdown, overall
score, analysis time, and diagnosis.

## Full Validation Log Summary

| Sample | Source | Manual judgment | Overall | Match | Notes |
|---|---|---|---:|---|---|
| `blur/blur_out_of_focus_03.jpg` | Wikimedia external | Out-of-focus image | 42 | Yes | Emulator App UI evidence captured; low sharpness and low overall match visible blur. |
| `blur/blur_subway_motion_01.jpg` | Wikimedia external | Motion blur from moving train | 87 | No | Offline replay counterexample: high-frequency background detail keeps score high. |
| `blur/blur_tanoura_motion_02.jpg` | Wikimedia external | Intentional motion blur | 27 | Yes | Offline replay shows low sharpness and low overall for visible blur. |
| `clear/clear_beetle_spider_02.jpg` | Wikimedia external | Clear macro/detail image | 65 | Partial | Offline replay gives acceptable but lower than expected score for a clear macro sample. |
| `clear/clear_butterfly_01.jpg` | Wikimedia external | Clear, sharp subject, normal exposure | 60 | Partial | Emulator App UI evidence captured; overall is acceptable, but color-cast warning remains. |
| `clear/clear_butterfly_03.jpg` | Wikimedia external | Clear focused subject | 71 | Partial | Offline replay gives moderate-high score, but color-cast penalty remains high. |
| `counterexamples/counterexample_bokeh_01.jpg` | Wikimedia external | Intentional night bokeh, human-acceptable | 23 | No | Offline replay intended failure: artistic blur/darkness is treated as low quality. |
| `counterexamples/counterexample_colored_lights_02.jpg` | Wikimedia external | Colored night lighting, human-acceptable | 40 | No | Offline replay intended failure: colored night lighting is penalized. |
| `counterexamples/counterexample_sunset_03.jpg` | Wikimedia external | Warm sunset light, human-acceptable | 79 | Partial | Offline replay is near acceptable, but warm color may still be treated as defect. |
| `formats/format_clear_jpeg_01.jpg` | Wikimedia-derived | JPEG format validation copy | 55 | Pending true-device confirmation | Offline replay ran; representative JPEG App UI evidence is covered by `clear_butterfly_01.jpg`. |
| `formats/format_clear_png_01.png` | Wikimedia-derived | PNG format validation copy | 60 | Yes | Emulator App UI evidence captured for PNG picker/load/analyze. |
| `formats/format_clear_webp_01.webp` | Wikimedia-derived | WebP format validation copy | 59 | Yes | Emulator App UI evidence captured for WebP picker/load/analyze. |
| `noisy/noisy_hong_kong_night_01.jpg` | Wikimedia external | Night image with visible noise | 60 | Partial | Offline replay mostly penalizes exposure, not noise. |
| `noisy/noisy_nind_keyboard_03.jpg` | Wikimedia external | High-ISO noise sample | 82 | No | Offline replay limitation: noise/texture can raise sharpness-like response. |
| `noisy/noisy_nind_stefantiek_02.jpg` | Wikimedia external | Natural Image Noise Dataset sample | 70 | Partial | Offline replay is only partly aligned because there is no dedicated noise metric. |
| `overexposed/overexposed_photo_01.jpg` | Wikimedia external | Washed-out highlights | 43 | Yes | Offline replay weak exposure and overall score match overexposure. |
| `overexposed/overexposed_sky_02.jpg` | Wikimedia external | Overexposed sky | 78 | Partial | Offline replay shows exposure penalty, but overall remains high. |
| `overexposed/overexposed_space_washed_03.jpg` | Wikimedia external | Washed-out overexposed space photograph | 31 | Yes | Offline replay exposure score drops to zero. |
| `underexposed/underexposed_pollen_03.jpg` | Wikimedia external | Dark low-exposure image | 27 | Yes | Offline replay exposure score drops to zero. |
| `underexposed/underexposed_space_02.jpg` | Wikimedia external | Underexposed space photograph | 38 | Partial | Offline replay overall is low, but exposure score is not the main penalty. |
| `underexposed/underexposed_sun_01.jpg` | Wikimedia external | Very dark frame | 27 | Yes | Offline replay exposure score drops to zero. |

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
- The App logic catches the out-of-focus blur sample with very low sharpness.
- The format samples confirm PNG and WebP decoding in the App UI.
- Global sharpness is vulnerable to high-frequency background detail and noise.
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
