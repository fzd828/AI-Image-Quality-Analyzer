# Screenshot Evidence Inventory

This folder contains Android Emulator screenshots captured during WS6 follow-up.
They are App UI evidence from `Medium_Phone` on Android API 36.1. They are not
Redmi K70E true-device screenshots.

## Captured Emulator Evidence

| Screenshot | Sample | Device | Status | Notes |
|---|---|---|---|---|
| `emulator_app_start.png` | App launch screen | Android Emulator `Medium_Phone`, API 36.1 | Captured | Shows the initial `Select image` state. |
| `emulator_clear_butterfly_jpeg_result.png` | `samples/clear/clear_butterfly_01.jpg` | Android Emulator `Medium_Phone`, API 36.1 | Captured | JPEG clear sample result, overall 60/100. |
| `emulator_blur_out_of_focus_jpeg_result.png` | `samples/blur/blur_out_of_focus_03.jpg` | Android Emulator `Medium_Phone`, API 36.1 | Captured | Blur sample result, overall 42/100. |
| `emulator_format_clear_png_result.png` | `samples/formats/format_clear_png_01.png` | Android Emulator `Medium_Phone`, API 36.1 | Captured | PNG format sample result, overall 60/100. |
| `emulator_format_clear_webp_result.png` | `samples/formats/format_clear_webp_01.webp` | Android Emulator `Medium_Phone`, API 36.1 | Captured | WebP format sample result, overall 59/100. |

## Simplified Chinese Emulator UI Evidence

These screenshots were captured after the App UI text was localized to
Simplified Chinese. They are Android Emulator evidence, not Redmi K70E
true-device evidence.

| Screenshot | Sample | Device | Status | Notes |
|---|---|---|---|---|
| `模拟器_简体中文_应用启动页.png` | App launch screen | Android Emulator `Medium_Phone`, API 36.1 | Captured | Shows the localized initial state. |
| `模拟器_简体中文_清晰蝴蝶_JPEG_分析结果.png` | `samples/clear/clear_butterfly_01.jpg` | Android Emulator `Medium_Phone`, API 36.1 | Captured | Localized JPEG clear sample result. |
| `模拟器_简体中文_失焦模糊_JPEG_分析结果.png` | `samples/blur/blur_out_of_focus_03.jpg` | Android Emulator `Medium_Phone`, API 36.1 | Captured | Localized blur sample result. |
| `模拟器_简体中文_PNG格式清晰样本_分析结果.png` | `samples/formats/format_clear_png_01.png` | Android Emulator `Medium_Phone`, API 36.1 | Captured | Localized PNG format sample result. |
| `模拟器_简体中文_WebP格式清晰样本_分析结果.png` | `samples/formats/format_clear_webp_01.webp` | Android Emulator `Medium_Phone`, API 36.1 | Captured | Localized WebP format sample result. |

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
| `redmi_format_clear_png_01_result.png` | `samples/formats/format_clear_png_01.png` | Redmi K70E | Pending manual capture | Confirms PNG picker/load/analyze flow on phone. |
| `redmi_format_clear_webp_01_result.png` | `samples/formats/format_clear_webp_01.webp` | Redmi K70E | Pending manual capture | Confirms WebP picker/load/analyze flow on phone. |
| `redmi_counterexample_bokeh_01_result.png` | `samples/counterexamples/counterexample_bokeh_01.jpg` | Redmi K70E | Pending manual capture | Shows an intentional counterexample in the App UI. |

The full structured score record is in `logs/analysis_log.csv`.
