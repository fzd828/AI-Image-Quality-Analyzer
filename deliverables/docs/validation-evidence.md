# Validation Evidence

## Sample Plan

Primary source: self-shot photos from Redmi K70E.

Capture 5-6 original photos:

- Normal clear scene
- Text or fine-detail scene
- Dark indoor or night scene
- Bright window, sky, or white-wall scene
- Colorful object, poster, or screen scene
- Optional natural counterexample such as sunset, shallow depth of field, or strong colored lighting

Create controlled derivatives from these originals:

- Clear
- Blurred
- Overexposed
- Underexposed
- Noisy
- Counterexamples

Export selected samples as JPEG, PNG, and WebP for required format validation.

## Result Table Template

| Sample | Format | Manual Judgment | Sharpness | Exposure | Contrast | Color/Noise | Overall | Match? | Notes |
|---|---|---|---:|---:|---:|---:|---:|---|---|
| clear_01.jpg | JPEG | Clear and normal | Fill after test | Fill after test | Fill after test | Fill after test | Fill after test | Fill after test | Baseline clear sample |
| blur_01.jpg | JPEG | Obvious blur | Fill after test | Fill after test | Fill after test | Fill after test | Fill after test | Fill after test | Expected low sharpness |
| over_01.png | PNG | Overexposed | Fill after test | Fill after test | Fill after test | Fill after test | Fill after test | Fill after test | Expected exposure warning |
| under_01.webp | WebP | Underexposed | Fill after test | Fill after test | Fill after test | Fill after test | Fill after test | Fill after test | Expected exposure warning |
| counter_night_01.jpg | JPEG | Normal night scene | Fill after test | Fill after test | Fill after test | Fill after test | Fill after test | Fill after test | Expected failure candidate |

## Evidence To Include

- App screenshots for representative images.
- Logs showing decode size and analysis time.
- APK install/run evidence.
- Notes about mismatches between score and manual judgment.
