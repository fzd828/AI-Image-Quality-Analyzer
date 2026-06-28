# WS2: Decode Metadata And Large Image Safety（图片解码、元数据与大图安全）

## Purpose

Create the image loading layer that extracts metadata, detects format, decodes a preview bitmap, and creates a safe downsampled analysis bitmap.

## Dependencies

- WS1 should be accepted or at least have a stable selected URI flow.

## Read First

- `AGENTS.md`
- `openspec/changes/option-1-image-quality-analyzer/specs/image-input-preview/spec.md`
- `docs/superpowers/plans/2026-06-28-workstream-execution-map.md`

## Allowed Write Scope

- Create: `app/app/src/main/java/com/example/imagequalityanalyzer/image/ImageLoader.kt`
- Modify: `app/app/src/main/java/com/example/imagequalityanalyzer/ui/ImageAnalyzerScreen.kt`
- Optional tests under `app/app/src/test/java/com/example/imagequalityanalyzer/image/`

## Required Interface

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

## Acceptance Criteria

- JPEG, PNG, and WebP can be identified from MIME type or file metadata.
- UI shows format, original dimensions, analysis dimensions, file size when available, and downsampling status.
- Large images use an analysis bitmap with long edge around 1280 px.
- Metadata/downsampling logic is separate from scoring logic.
- No OOM-prone full-resolution analysis path is introduced.

## Completion Notes

- Status: completed locally on 2026-06-28.
- Implementation:
  - `ImageLoader` reads source bounds first, detects JPEG/PNG/WebP from MIME type or display name, reads file size when available, and returns `LoadedImage`.
  - Preview decoding and analysis decoding are separate; the analysis bitmap is scaled to a long edge around 1280 px.
  - `ImageAnalyzerScreen` displays format, original dimensions, analysis dimensions, optional file size, and downsampling status.
- Tests and verification:
  - TDD red run: `.\gradlew.bat :app:testDebugUnitTest` failed before `ImageLoader` existed with unresolved `ImageLoader` references.
  - Green run: `.\gradlew.bat :app:testDebugUnitTest` succeeded.
  - Build run: `.\gradlew.bat :app:assembleDebug` succeeded.
- Notes:
  - Real-device visual validation remains part of later validation/evidence workstreams.
  - No scoring logic was added in WS2.
