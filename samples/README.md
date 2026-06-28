# Redmi K70E Validation Sample Set

This folder is reserved for validation images for the ISBG 2026 Option 1 image
quality analyzer. The contest asks for comparison samples and a comparison
between app scores and human judgment; it does not require every sample to be
self-shot. Redmi K70E self-shot images are preferred for stronger evidence, and
external images may be used when their source, author, and license are recorded.
Do not add external copyrighted images unless the source and license are
documented in the notes column below.

Open-license external validation images have been added for every required
comparison category. The tables below also preserve a Redmi K70E capture plan in
case self-shot images are added later. Full source and license records are in
`samples/SOURCES.md`.

## Folder Layout

| Folder | Purpose |
|---|---|
| `originals/` | Unmodified Redmi K70E source photos. Keep these as the source of truth. |
| `clear/` | Clear baseline samples copied or exported from originals. |
| `blur/` | Motion blur, out-of-focus, or controlled blur derivatives. |
| `overexposed/` | Bright-window, sky, white-wall, or edited bright derivatives. |
| `underexposed/` | Dark indoor, night, or edited dark derivatives. |
| `noisy/` | Low-light noisy shots or controlled noise derivatives. |
| `counterexamples/` | Natural cases likely to expose scoring limits. |
| `formats/` | Format validation copies: at least one JPEG, PNG, and WebP. |

## Capture Guide

Use the Redmi K70E camera app unless a note says otherwise. Prefer self-shot
photos of non-private scenes. Avoid faces, ID cards, private screens, license
plates, and copyrighted artwork.

Use local time for the schedule. The locations below are examples; choose the
nearest safe equivalent if the exact place is not available.

| Time Window | Location | Shot | File To Create | Why It Is Needed |
|---|---|---|---|---|
| 09:00-11:00 | Window-side desk, balcony, courtyard, or quiet street | Normal daylight scene with steady hands and focus tapped on the subject | `orig_clear_scene_01.jpg` | Baseline clear image for normal quality. |
| 09:00-11:00 | Desk, book page, keyboard, package label, or textured object | Fine text/detail scene; also take one intentionally shaky version | `orig_text_detail_01.jpg` | Clear/detail sample and blur comparison source. |
| 12:00-14:00 | Bright window, sky, white wall, sunlit ground, or bright corridor | Bright scene with some highlights close to white | `orig_bright_scene_01.jpg` | Overexposure sample source. |
| 18:30-20:00 | Dim room corner, hallway, indoor shelf, or shaded outdoor area | Dark scene without flash; keep the phone steady | `orig_dark_scene_01.jpg` | Underexposure sample source. |
| 20:00-22:00 | Low-light room, night street, or window view after dark | Low-light shot without flash, accepting visible grain/noise | `orig_noisy_night_01.jpg` | Noisy sample source. |
| Sunset, evening, or night | Sunset view, colored lamp, night sign, or shallow depth-of-field object | A photo that looks acceptable to a human but may be penalized by simple metrics | `orig_counterexample_01.jpg` | Counterexample for limitation analysis. |

Practical capture rules:

- Keep original camera files unchanged in `samples/originals/`.
- Take 2-3 candidate shots for each scene, then keep the best representative
  one or two.
- Turn off beautification/filter effects when possible.
- Do not use flash for dark/noisy samples unless documenting it as an
  intentional variant.
- Use the same phone orientation where convenient, but orientation is not a
  requirement.
- If a scene contains private information, retake the photo before adding it to
  this repository.

Recommended originals:

| Original ID | Scene To Capture | Target Use | Status |
|---|---|---|---|
| `orig_clear_scene_01.jpg` | Daylight desk, plant, street, or room scene with normal focus | Clear baseline | Pending |
| `orig_text_detail_01.jpg` | Printed text, book page, keyboard, or detailed object | Clear and blur comparison | Pending |
| `orig_bright_scene_01.jpg` | Bright window, sky, or white wall | Overexposed category | Pending |
| `orig_dark_scene_01.jpg` | Dark indoor corner or night scene | Underexposed and noisy category | Pending |
| `orig_color_scene_01.jpg` | Colorful object, poster, fruit, or screen-safe object | Color and contrast validation | Pending |
| `orig_counterexample_01.jpg` | Night, sunset, shallow depth of field, or colored lighting | Counterexample analysis | Pending |

## Where To Put The Photos

After capture, copy files into these folders:

| Folder | Put These Files Here | Minimum Before WS6 |
|---|---|---:|
| `originals/` | The unmodified Redmi K70E source photos listed above. | 5-6 |
| `clear/` | Copies or exports of normal, sharp photos. | 2 |
| `blur/` | Intentionally shaky/out-of-focus photos or blur derivatives. | 2 |
| `overexposed/` | Naturally bright or brightened copies. | 2 |
| `underexposed/` | Naturally dark or darkened copies. | 2 |
| `noisy/` | Low-light noisy shots or noise derivatives. | 2 |
| `counterexamples/` | Human-acceptable but algorithm-challenging photos. | 1 |
| `formats/` | One JPEG, one PNG, and one WebP copy for format validation. | 3 |

## Naming Rules

Use lowercase ASCII names with this pattern:

```text
<category>_<scene>_<index>.<extension>
```

Examples:

```text
clear_text_01.jpg
blur_text_01.jpg
overexposed_window_01.png
underexposed_room_01.webp
noisy_night_01.jpg
counterexample_sunset_01.jpg
format_clear_jpeg_01.jpg
format_clear_png_01.png
format_clear_webp_01.webp
```

Keep original source files in `originals/` and record derived files in the
sample inventory. If a file is transformed, keep the operation simple and
repeatable, such as Gaussian blur, brightness adjustment, exposure adjustment,
or noise addition.

## Required Coverage Checklist

Each quality category should end with 2-3 real samples or a clear pending note.
The current state uses documented open-license external images.

| Category | Needed Real Files | Current Files | Manual Action Needed |
|---|---:|---:|---|
| Clear | 2-3 | 3 | Optional: replace or supplement with Redmi K70E self-shot clear scenes. |
| Blur | 2-3 | 3 | Optional: replace or supplement with Redmi K70E blur shots. |
| Overexposed | 2-3 | 3 | Optional: replace or supplement with Redmi K70E bright scenes. |
| Underexposed | 2-3 | 3 | Optional: replace or supplement with Redmi K70E dark scenes. |
| Noisy | 2-3 | 3 | Optional: replace or supplement with Redmi K70E low-light noisy scenes. |
| Counterexamples | 1-3 | 3 | Optional: replace or supplement with Redmi K70E night/sunset/cast examples. |
| Formats | 3+ | 3 | JPEG, PNG, and WebP validation files are present. |

## Downloaded External Samples

The current sample set was found online through Wikimedia Commons. These files
are not Redmi K70E self-shot samples, but they are included as official
comparison samples because the inventory preserves the source page, author,
license, manual judgment, and preparation notes. Keep Redmi K70E samples as
stronger additional evidence if they become available.

See `samples/SOURCES.md` for the complete per-file source inventory.

## Sample Inventory Template

Fill one row per real or derived sample after capture.

| File | Folder | Source Original | Capture Device | Format | Scene | Preparation | Manual Judgment | Expected App Behavior | Notes |
|---|---|---|---|---|---|---|---|---|---|
| `clear_text_01.jpg` | `clear/` | `orig_text_detail_01.jpg` | Redmi K70E | JPEG | Printed text/detail | None or exported copy | Clear and sharp | High sharpness, balanced exposure | Pending capture |
| `blur_text_01.jpg` | `blur/` | `orig_text_detail_01.jpg` | Redmi K70E | JPEG | Printed text/detail | Intentional motion blur or blur derivative | Obvious blur | Low sharpness warning | Pending capture |
| `overexposed_window_01.png` | `overexposed/` | `orig_bright_scene_01.jpg` | Redmi K70E | PNG | Bright window/sky | Brightened or naturally clipped | Too bright/clipped | Exposure warning | Pending capture |
| `underexposed_room_01.webp` | `underexposed/` | `orig_dark_scene_01.jpg` | Redmi K70E | WebP | Dark room/night | Darkened or naturally low light | Too dark | Exposure warning | Pending capture |
| `noisy_night_01.jpg` | `noisy/` | `orig_dark_scene_01.jpg` | Redmi K70E | JPEG | Low-light scene | None or noise derivative | Visible noise | Possible low score or limitation note | Pending capture |
| `counterexample_night_01.jpg` | `counterexamples/` | `orig_counterexample_01.jpg` | Redmi K70E | JPEG | Natural night/sunset/shallow DOF | None | Acceptable artistic photo | Candidate mismatch for docs | Pending capture |
| `format_clear_jpeg_01.jpg` | `formats/` | `orig_clear_scene_01.jpg` | Redmi K70E | JPEG | Normal clear scene | JPEG export/copy | Format support sample | Loads and analyzes | Pending capture |
| `format_clear_png_01.png` | `formats/` | `orig_clear_scene_01.jpg` | Redmi K70E | PNG | Normal clear scene | PNG export | Format support sample | Loads and analyzes | Pending capture |
| `format_clear_webp_01.webp` | `formats/` | `orig_clear_scene_01.jpg` | Redmi K70E | WebP | Normal clear scene | WebP export | Format support sample | Loads and analyzes | Pending capture |

## Manual Collection Steps

1. Capture the six recommended originals with Redmi K70E.
2. Copy originals into `samples/originals/` without editing.
3. Select or export 2-3 files per category into the category folders.
4. Add one JPEG, one PNG, and one WebP copy into `samples/formats/`.
5. Update the inventory rows above with real filenames and preparation notes.
6. Use these files in WS6 to run the app and fill validation scores.

## Photo Acceptance Check Method

When real images are added, evaluate each file before WS6. A sample can be
marked `Accepted`, `Borderline`, or `Replace`.

Manual checks:

| Check | Accepted | Borderline | Replace |
|---|---|---|---|
| Source | Self-shot Redmi K70E photo, documented derivative, or documented open-license external image | Source likely correct but notes missing | Unknown source or external image without license |
| Privacy | No faces, IDs, private screens, addresses, or plates | Minor non-sensitive background details | Obvious private or identifying content |
| Category fit | Clearly matches the target category | Could fit, but not visually obvious | Does not match the category |
| Reproducibility | Source original and transformation are documented | Filename is clear but notes incomplete | Cannot tell how the sample was made |
| Format | JPEG, PNG, or WebP as required | Supported format but wrong folder | Unsupported or mislabeled format |

Category-specific visual checks:

| Category | Accepted Signal | Common Reason To Replace |
|---|---|---|
| Clear | Main subject is sharp, exposure looks normal, and details are readable. | Subject is soft, too dark, too bright, or mostly blank. |
| Blur | Motion blur or out-of-focus softness is obvious at normal viewing size. | Looks nearly as sharp as the clear baseline. |
| Overexposed | Bright areas are visibly clipped or the scene is washed out. | It is only a normal bright photo with preserved detail. |
| Underexposed | Important content is hard to see because the image is too dark. | It is a normal night photo with enough detail and intended mood. |
| Noisy | Grain/noise is visible in flat or dark regions. | Noise is not visible, or blur is the main defect instead. |
| Counterexamples | Human judgment says acceptable, but simple metrics may penalize it. | It is just a bad photo rather than a meaningful limitation case. |
| Formats | File opens as the named JPEG, PNG, or WebP format. | Extension and actual format do not match. |

Technical checks to run after adding images:

```powershell
Get-ChildItem -Recurse samples -Include *.jpg,*.jpeg,*.png,*.webp -File |
  Select-Object DirectoryName,Name,Length
```

The review pass should confirm:

- Each required folder has the expected number of image files.
- `samples/formats/` includes at least one `.jpg` or `.jpeg`, one `.png`, and
  one `.webp`.
- Filenames follow the naming rules.
- Any derived sample has its source original named in the inventory.
- The inventory notes say whether the file is original, copied, exported,
  blurred, brightened, darkened, or noise-added.

## Notes For WS6

WS6 should not treat placeholder `.gitkeep` files as samples. Only image files
with `.jpg`, `.jpeg`, `.png`, or `.webp` extensions count as validation samples.
HEIC may be mentioned in documentation only and is not part of the required
supported format set.
