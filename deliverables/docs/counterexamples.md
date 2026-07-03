# Counterexamples

This document records scoring failures and limitations found during the WS6
validation evidence pass. The examples below use documented Wikimedia external
samples from `samples/SOURCES.md`; they are not Redmi K70E self-shot images.

## Counterexample 1: Motion Blur Scored Too High

Sample: `samples/blur/blur_subway_motion_01.jpg`

Manual judgment: motion blur from a moving train.

Recalculated Sharpness-Calibrate scoring-log result:

- Sharpness: 90
- Exposure: 79
- Contrast: 100
- Color cast: 100
- Overall: 90
- Match: No

Failure reason:

The image contains visible motion blur, but also has strong lines, edges, and
station detail. The upgraded local Laplacian and Tenengrad signals still
respond to those high-frequency structures, so the sharpness score stays high
even though the human-visible subject motion is blurred.

Possible improvement:

Use a blur metric that considers directional motion blur or compares local edge
coherence, not only local/global edge strength.

## Improved Case: Clear Image No Longer Penalized Mainly By Sharpness

Sample: `samples/clear/clear_butterfly_01.jpg`

Manual judgment: clear, sharp subject, normal exposure.

Previous emulator App UI result before Sharpness-Calibrate:

- Sharpness: 36
- Exposure: 94
- Contrast: 74
- Color cast: 30
- Overall: 60
- Match: Partial

Recalculated Sharpness-Calibrate result:

- Sharpness: 51
- Exposure: 95
- Contrast: 67
- Color cast: 37
- Overall: 65
- Match: Yes

What improved:

The local-block Laplacian score gives more weight to the sharp subject/detail
regions, so this clear natural sample no longer sits in the 30-40 sharpness
band. This is the main reason for the Sharpness-Calibrate change.

Remaining limitation:

The color-cast metric still treats some natural color imbalance as a possible
white-balance issue. Exact UI screenshots should be recaptured because the
existing emulator screenshot predates this recalculation.

## Counterexample 2: High-ISO Noise Scored As Good

Sample: `samples/noisy/noisy_nind_keyboard_03.jpg`

Manual judgment: high-ISO noise sample.

Current emulator App UI result:

- Sharpness: 96
- Exposure: 71
- Contrast: 89
- Color cast: 98
- Overall: 87
- Match: No

Failure reason:

The app does not have a dedicated noise metric. Texture and noise can raise
both local Laplacian and Sobel/Tenengrad responses, causing a noisy image to
look sharper to the algorithm. The current screenshot is
`screenshots/emulator_noisy_nind_keyboard_result.png`; it is Android Emulator
evidence, not Redmi K70E true-device evidence.

Possible improvement:

Add a noise estimate on flat regions and combine it with sharpness so noise does
not falsely improve perceived detail.

## Counterexample 3: Artistic Night Bokeh Penalized

Sample: `samples/counterexamples/counterexample_bokeh_01.jpg`

Manual judgment: intentional night bokeh, human-acceptable.

Recalculated Sharpness-Calibrate scoring-log result:

- Sharpness: 6
- Exposure: 0
- Contrast: 53
- Color cast: 80
- Overall: 25
- Match: No

Failure reason:

The algorithm sees blur and dark pixels but does not understand that the bokeh
and low-light mood are intentional. The local-block upgrade does not solve
scene intent or artistic blur.

Possible improvement:

Document this as a limitation, or add scene categories such as night/artistic
mode before applying strict exposure and sharpness penalties.

## Real-Device Counterexample Evidence Pending

The counterexample scores above come from the structured WS6 score log at
`logs/analysis_log.csv`. Some rows are emulator App UI results, while rows
without screenshots remain offline scoring replays against the sample files.
Real Redmi K70E App screenshots are still pending and should be added before
final packaging if the device is available.
