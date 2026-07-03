# Screenshot Evidence Inventory

This folder contains Android Emulator screenshots captured from the current
localized App UI on `Medium_Phone` / Android API 36.1.

These screenshots are emulator evidence, not Redmi K70E true-device evidence.
The older pre-calibration screenshots were removed before this recapture pass,
so final documentation should reference only the files that currently exist.

## Current Emulator Evidence

| Screenshot | Sample | Device | Mode | Status | Notes |
|---|---|---|---|---|---|
| `emulator_noisy_hong_kong_night_result.png` | `samples/noisy/noisy_hong_kong_night_01.jpg` | Android Emulator `Medium_Phone`, API 36.1 | Fine mode 2560 | Captured | Noisy night sample result, overall 67/100. |
| `emulator_noisy_nind_keyboard_result.png` | `samples/noisy/noisy_nind_keyboard_03.jpg` | Android Emulator `Medium_Phone`, API 36.1 | Fine mode 2560 | Captured | High-ISO keyboard noise sample result, overall 87/100. |
| `emulator_noisy_nind_stefantiek_result.png` | `samples/noisy/noisy_nind_stefantiek_02.jpg` | Android Emulator `Medium_Phone`, API 36.1 | Fine mode 2560 | Captured | NIND noisy portrait/detail sample result, overall 78/100. |

## Emulator Evidence To Recapture If Needed

These screenshots existed in an older pass but were intentionally removed
because they predated the current scoring/UI state. Recapture them from the
current App before final packaging if reviewer-facing screenshots are needed.

| Screenshot | Sample | Device | Status | Notes |
|---|---|---|---|---|
| `emulator_app_start.png` | App launch screen | Android Emulator `Medium_Phone`, API 36.1 | Pending recapture | Current localized launch screen. |
| `emulator_clear_butterfly_jpeg_result.png` | `samples/clear/clear_butterfly_01.jpg` | Android Emulator `Medium_Phone`, API 36.1 | Pending recapture | Representative clear-image run. |
| `emulator_blur_out_of_focus_jpeg_result.png` | `samples/blur/blur_out_of_focus_03.jpg` | Android Emulator `Medium_Phone`, API 36.1 | Pending recapture | Representative blur run. |
| `emulator_format_clear_png_result.png` | `samples/formats/format_clear_png_01.png` | Android Emulator `Medium_Phone`, API 36.1 | Pending recapture | PNG picker/load/analyze flow. |
| `emulator_format_clear_webp_result.png` | `samples/formats/format_clear_webp_01.webp` | Android Emulator `Medium_Phone`, API 36.1 | Pending recapture | WebP picker/load/analyze flow. |

## Pending Redmi K70E Evidence

No Redmi K70E true-device screenshots are present yet. The items below should be
captured only when the real phone is available. Do not relabel emulator or
Wikimedia evidence as Redmi K70E evidence.

| Screenshot | Sample | Device | Status | Notes |
|---|---|---|---|---|
| `redmi_app_start.png` | App launch screen | Redmi K70E | Pending manual capture | Confirms the app starts on the target phone. |
| `redmi_clear_butterfly_01_result.png` | `samples/clear/clear_butterfly_01.jpg` | Redmi K70E | Pending manual capture | Representative clear-image run. |
| `redmi_blur_out_of_focus_03_result.png` | `samples/blur/blur_out_of_focus_03.jpg` | Redmi K70E | Pending manual capture | Representative blur run. |
| `redmi_overexposed_photo_01_result.png` | `samples/overexposed/overexposed_photo_01.jpg` | Redmi K70E | Pending manual capture | Representative exposure-warning run. |
| `redmi_noisy_hong_kong_night_result.png` | `samples/noisy/noisy_hong_kong_night_01.jpg` | Redmi K70E | Pending manual capture | Representative noisy-night run. |
| `redmi_noisy_nind_keyboard_result.png` | `samples/noisy/noisy_nind_keyboard_03.jpg` | Redmi K70E | Pending manual capture | Representative high-ISO noise run. |
| `redmi_noisy_nind_stefantiek_result.png` | `samples/noisy/noisy_nind_stefantiek_02.jpg` | Redmi K70E | Pending manual capture | Representative NIND noisy sample run. |
| `redmi_format_clear_png_01_result.png` | `samples/formats/format_clear_png_01.png` | Redmi K70E | Pending manual capture | Confirms PNG picker/load/analyze flow on phone. |
| `redmi_format_clear_webp_01_result.png` | `samples/formats/format_clear_webp_01.webp` | Redmi K70E | Pending manual capture | Confirms WebP picker/load/analyze flow on phone. |
| `redmi_counterexample_bokeh_01_result.png` | `samples/counterexamples/counterexample_bokeh_01.jpg` | Redmi K70E | Pending manual capture | Shows an intentional counterexample in the App UI. |

The full structured score record is in `logs/analysis_log.csv`.
