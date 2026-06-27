# Image Quality Analyzer Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build a Kotlin + Jetpack Compose Android app and evidence package for ISBG 2026 AI Homework Contest Option 1.

**Architecture:** The Android app is split into UI, image decode, image analysis, scoring, and explanation units. The submission package is treated as a first-class deliverable, with docs, samples, screenshots, logs, APK, and source arranged for reviewer inspection.

**Tech Stack:** Android native, Kotlin, Jetpack Compose, Android Photo Picker or Storage Access Framework, Bitmap/ImageDecoder APIs, Markdown documentation, CSV validation logs.

## Global Constraints

- Deadline: 2026-07-07 18:00.
- Primary target: structured ZIP package; GitHub is optional after ZIP completion.
- Device evidence target: Redmi K70E real-device validation.
- Required image formats: JPEG, PNG, WebP.
- HEIC is documentation-only unless time remains and device support is reliable.
- Core metrics: sharpness, exposure, contrast, color cast.
- No deep learning model in core scope.
- No complex charting or JSON export in core scope.
- Every feature must produce evidence for at least one contest scoring category.

---

## File Structure

- `app/`: Android project root created in Android Studio.
- `app/app/src/main/java/.../MainActivity.kt`: Compose entrypoint and screen wiring.
- `app/app/src/main/java/.../ui/ImageAnalyzerScreen.kt`: image picker, preview, metadata, score display.
- `app/app/src/main/java/.../image/ImageLoader.kt`: URI metadata extraction, format detection, safe decode, downsampling.
- `app/app/src/main/java/.../analysis/ImageQualityAnalyzer.kt`: raw metric calculations.
- `app/app/src/main/java/.../analysis/ImageQualityScorer.kt`: raw metrics to 0-100 sub-scores and overall score.
- `app/app/src/main/java/.../analysis/QualityExplanation.kt`: human-readable diagnosis text.
- `app/app/src/test/java/.../analysis/ImageQualityAnalyzerTest.kt`: unit tests for synthetic image metrics.
- `deliverables/docs/`: final required documentation drafts.
- `samples/`: validation samples grouped by category.
- `screenshots/`: Redmi K70E screenshots.
- `logs/analysis_log.csv`: validation results and runtime evidence.
- `apk/`: final installable APK.

## Task 1: Create Android Project Skeleton

**Files:**
- Create: `app/` through Android Studio using Empty Activity with Jetpack Compose.
- Create: `app/README.md`

**Interfaces:**
- Produces a buildable Android project using Kotlin and Compose.
- Later tasks rely on package name and Gradle project structure.

- [ ] **Step 1: Create project**

Use Android Studio:

```text
New Project -> Empty Activity
Name: ImageQualityAnalyzer
Package name: com.example.imagequalityanalyzer
Language: Kotlin
Minimum SDK: API 26 or higher
Build configuration language: Kotlin DSL if available
```

- [ ] **Step 2: Build the empty app**

Run in Android Studio:

```text
Build -> Make Project
```

Expected: build succeeds without source edits.

- [ ] **Step 3: Run on Redmi K70E or emulator**

Expected: default Compose screen opens.

- [ ] **Step 4: Add project note**

Create `app/README.md`:

```markdown
# Android App

This directory contains the Kotlin + Jetpack Compose implementation for the image quality analyzer.

Target device for validation: Redmi K70E.
```

## Task 2: Implement Image Picker And Preview

**Files:**
- Modify: `app/app/src/main/java/com/example/imagequalityanalyzer/MainActivity.kt`
- Create: `app/app/src/main/java/com/example/imagequalityanalyzer/ui/ImageAnalyzerScreen.kt`

**Interfaces:**
- Produces selected image URI state.
- Later decode and analysis tasks consume selected URI.

- [ ] **Step 1: Add Compose screen**

Create `ImageAnalyzerScreen` with:

```kotlin
@Composable
fun ImageAnalyzerScreen() {
    var selectedUri by remember { mutableStateOf<Uri?>(null) }
    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedUri = uri
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { picker.launch("image/*") }) {
            Text("Select image")
        }
        Spacer(modifier = Modifier.height(16.dp))
        selectedUri?.let { uri ->
            AsyncImage(
                model = uri,
                contentDescription = "Selected image",
                modifier = Modifier.fillMaxWidth().height(280.dp),
                contentScale = ContentScale.Fit
            )
        } ?: Text("No image selected")
    }
}
```

If Coil is not available, use Android bitmap decoding in Task 3 and display with `Image(bitmap = ...)`.

- [ ] **Step 2: Wire `MainActivity`**

```kotlin
setContent {
    MaterialTheme {
        ImageAnalyzerScreen()
    }
}
```

- [ ] **Step 3: Manual test**

Run on device, tap `Select image`, select a photo, verify preview appears.

## Task 3: Add Metadata, Format Detection, And Safe Decode

**Files:**
- Create: `app/app/src/main/java/com/example/imagequalityanalyzer/image/ImageLoader.kt`
- Modify: `ImageAnalyzerScreen.kt`

**Interfaces:**
- Produces `LoadedImage`.
- Later analysis consumes `analysisBitmap`.

```kotlin
data class LoadedImage(
    val previewBitmap: Bitmap,
    val analysisBitmap: Bitmap,
    val format: String,
    val originalWidth: Int,
    val originalHeight: Int,
    val analysisWidth: Int,
    val analysisHeight: Int,
    val fileSizeBytes: Long?,
    val downsampled: Boolean
)
```

- [ ] **Step 1: Implement metadata decode**

Use `ImageDecoder` on Android P+ and `BitmapFactory` fallback if needed. Decode bounds first when using `BitmapFactory`.

- [ ] **Step 2: Implement format detection**

Detect by MIME type from `ContentResolver.getType(uri)`:

```kotlin
fun mimeToFormat(mime: String?): String = when (mime?.lowercase()) {
    "image/jpeg", "image/jpg" -> "JPEG"
    "image/png" -> "PNG"
    "image/webp" -> "WebP"
    "image/heic", "image/heif" -> "HEIC"
    else -> "Unknown"
}
```

- [ ] **Step 3: Implement analysis downsampling**

Normalize analysis bitmap to long edge near 1280 px:

```kotlin
fun computeAnalysisSize(width: Int, height: Int, maxLongEdge: Int = 1280): Pair<Int, Int> {
    val longEdge = maxOf(width, height)
    if (longEdge <= maxLongEdge) return width to height
    val scale = maxLongEdge.toFloat() / longEdge.toFloat()
    return (width * scale).roundToInt().coerceAtLeast(1) to
        (height * scale).roundToInt().coerceAtLeast(1)
}
```

- [ ] **Step 4: Display metadata**

Show format, original size, analysis size, file size, and whether downsampling occurred.

- [ ] **Step 5: Manual test**

Test one JPEG, one PNG, one WebP. Record whether all preview and metadata correctly.

## Task 4: Implement Core Image Metrics

**Files:**
- Create: `app/app/src/main/java/com/example/imagequalityanalyzer/analysis/ImageQualityAnalyzer.kt`
- Create: `app/app/src/test/java/com/example/imagequalityanalyzer/analysis/ImageQualityAnalyzerTest.kt`

**Interfaces:**
- Consumes `Bitmap`.
- Produces `QualityMetrics`.

```kotlin
data class QualityMetrics(
    val laplacianVariance: Double,
    val meanLuminance: Double,
    val underexposedRatio: Double,
    val overexposedRatio: Double,
    val contrastStdDev: Double,
    val colorCastDistance: Double
)
```

- [ ] **Step 1: Implement grayscale/luminance extraction**

Use approximate luminance:

```kotlin
val y = 0.299 * red + 0.587 * green + 0.114 * blue
```

- [ ] **Step 2: Implement Laplacian variance**

Use 3x3 kernel:

```text
0  1  0
1 -4  1
0  1  0
```

Compute variance of the response values.

- [ ] **Step 3: Implement exposure statistics**

Count:

```text
underexposedRatio = pixels with luminance < 25 / total pixels
overexposedRatio = pixels with luminance > 245 / total pixels
meanLuminance = average luminance
```

- [ ] **Step 4: Implement contrast**

Use standard deviation of luminance.

- [ ] **Step 5: Implement color cast**

Compute average RGB values excluding luminance below 20 and above 245, then measure channel imbalance:

```text
colorCastDistance = max(abs(rAvg-gAvg), abs(gAvg-bAvg), abs(rAvg-bAvg))
```

- [ ] **Step 6: Unit test with synthetic bitmaps**

Create small synthetic images:

- uniform gray should have low contrast and low Laplacian variance.
- checkerboard should have higher contrast and sharpness.
- all-white image should have high overexposed ratio.

## Task 5: Convert Metrics To Scores And Explanation

**Files:**
- Create: `app/app/src/main/java/com/example/imagequalityanalyzer/analysis/ImageQualityScorer.kt`
- Create: `app/app/src/main/java/com/example/imagequalityanalyzer/analysis/QualityExplanation.kt`

**Interfaces:**
- Consumes `QualityMetrics`.
- Produces `QualityResult`.

```kotlin
data class QualityResult(
    val sharpnessScore: Int,
    val exposureScore: Int,
    val contrastScore: Int,
    val colorCastScore: Int,
    val overallScore: Int,
    val explanation: String
)
```

- [ ] **Step 1: Implement score clamp**

```kotlin
fun clampScore(value: Double): Int = value.roundToInt().coerceIn(0, 100)
```

- [ ] **Step 2: Implement initial scoring rules**

Use simple explainable mappings:

```text
sharpnessScore: map Laplacian variance from 20..800 to 0..100
exposureScore: start from 100, subtract underexposedRatio*120 and overexposedRatio*120, subtract mean luminance distance from 128 scaled by 0.25
contrastScore: map luminance std dev from 10..70 to 0..100
colorCastScore: start from 100, subtract colorCastDistance*1.2
overallScore = 0.35*sharpness + 0.30*exposure + 0.20*contrast + 0.15*colorCast
```

- [ ] **Step 3: Implement explanation**

Example sentence:

```text
Slight blur risk, exposure normal, contrast moderate, color balance acceptable.
```

- [ ] **Step 4: Display result in UI**

Show sub-scores, overall score, explanation, analysis time, original size, analysis size.

## Task 6: Create Validation Samples

**Files:**
- Create: `samples/originals/`
- Create: `samples/clear/`
- Create: `samples/blur/`
- Create: `samples/overexposed/`
- Create: `samples/underexposed/`
- Create: `samples/noisy/`
- Create: `samples/counterexamples/`
- Create: `samples/formats/`

**Interfaces:**
- Produces sample set for validation and screenshots.

- [ ] **Step 1: Capture Redmi K70E originals**

Take 5-6 photos:

```text
clear normal scene
text/detail scene
dark indoor or night scene
bright window/sky/white wall scene
colorful scene
counterexample scene such as night, sunset, shallow depth of field
```

- [ ] **Step 2: Create derivatives**

Use phone gallery editor, desktop image editor, or later a script to create blur, overexposed, underexposed, and noisy versions.

- [ ] **Step 3: Export formats**

Ensure at least one JPEG, one PNG, and one WebP sample are available.

## Task 7: Run Validation And Produce Evidence

**Files:**
- Create: `logs/analysis_log.csv`
- Update: `deliverables/docs/validation-evidence.md`
- Update: `deliverables/docs/counterexamples.md`

**Interfaces:**
- Consumes app and samples.
- Produces final evidence.

- [ ] **Step 1: Define CSV header**

```csv
sample,format,manual_judgment,original_size,analysis_size,downsampled,analysis_ms,sharpness,exposure,contrast,color_cast,overall,match,notes
```

- [ ] **Step 2: Run every sample**

For each sample, record app result and manual judgment.

- [ ] **Step 3: Capture screenshots**

Save screenshots into `screenshots/`:

```text
home screen
clear sample result
blur sample result
overexposed sample result
counterexample result
metadata/downsampling evidence
```

- [ ] **Step 4: Fill validation document**

Replace "Fill after test" cells with measured values.

## Task 8: Complete Contest Documents

**Files:**
- Update: `deliverables/README.md`
- Update: `deliverables/docs/constraints-and-decisions.md`
- Update: `deliverables/docs/algorithm-notes.md`
- Update: `deliverables/docs/ai-collaboration.md`
- Update: `deliverables/docs/scoring-alignment.md`

**Interfaces:**
- Produces required reviewer-facing documents.

- [ ] **Step 1: README**

Include goal, non-goals, supported formats, run instructions, package structure, and completion criteria.

- [ ] **Step 2: Constraints and decisions**

Keep final Chinese version within 500 Chinese characters.

- [ ] **Step 3: Algorithm notes**

For each metric, include formula idea, parameter meaning, limitation, and one failure mode.

- [ ] **Step 4: AI collaboration notes**

Record AI contributions, human verification, question iterations, and at least one rejected AI suggestion.

- [ ] **Step 5: Scoring alignment**

Map evidence to passion, critical thinking, questioning ability, integration ability, and learning ability.

## Task 9: Build APK And Create ZIP Submission

**Files:**
- Create: `apk/`
- Create: final ZIP package under project root.

**Interfaces:**
- Produces final submission package.

- [ ] **Step 1: Build APK**

Use Android Studio:

```text
Build -> Build App Bundle(s) / APK(s) -> Build APK(s)
```

Copy APK to:

```text
apk/image-quality-analyzer.apk
```

- [ ] **Step 2: Verify install on Redmi K70E**

Install APK, open app, select at least one image, verify analysis result.

- [ ] **Step 3: Create final folder**

Use naming rule:

```text
XX地域-图像质量分析器-XX姓名/
```

Include:

```text
app/
apk/
docs/
samples/
screenshots/
logs/
```

- [ ] **Step 4: Final self-review**

Check:

```text
Can reviewer install APK?
Can reviewer find source?
Can reviewer understand goal and non-goals?
Can reviewer see validation evidence?
Can reviewer see counterexample?
Can reviewer see AI collaboration transparency?
Can reviewer map work to scoring rubric?
```

## Spec Coverage Self-Review

- Image input and preview: Tasks 2 and 3.
- JPEG/PNG/WebP support: Task 3 and Task 6.
- Large image downsampling: Task 3 and Task 7.
- Sharpness/exposure/contrast/color cast: Tasks 4 and 5.
- Overall score and diagnosis: Task 5.
- Validation evidence and counterexample: Tasks 6 and 7.
- AI collaboration and rubric alignment: Task 8.
- ZIP-first final submission: Task 9.

