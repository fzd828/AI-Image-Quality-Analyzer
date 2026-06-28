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
