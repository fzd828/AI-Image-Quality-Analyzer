package com.example.imagequalityanalyzer.image

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import kotlin.math.max
import kotlin.math.roundToInt

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

data class BitmapSize(
    val width: Int,
    val height: Int,
    val downsampled: Boolean
)

object ImageLoader {
    private const val ANALYSIS_LONG_EDGE = 1280
    private const val PREVIEW_LONG_EDGE = 1600

    fun load(context: Context, uri: Uri): LoadedImage? = runCatching {
        val resolver = context.contentResolver
        val metadata = metadataOrFallback {
            queryMetadata(resolver, uri)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return@runCatching loadWithImageDecoder(resolver, uri, metadata)
        }

        val bounds = readBounds(resolver, uri) ?: return@runCatching null
        if (bounds.width <= 0 || bounds.height <= 0) {
            return@runCatching null
        }

        val previewBitmap = decodeBitmapForLongEdge(
            resolver = resolver,
            uri = uri,
            originalWidth = bounds.width,
            originalHeight = bounds.height,
            maxLongEdge = PREVIEW_LONG_EDGE
        ) ?: return@runCatching null

        val analysisBitmap = decodeBitmapForLongEdge(
            resolver = resolver,
            uri = uri,
            originalWidth = bounds.width,
            originalHeight = bounds.height,
            maxLongEdge = ANALYSIS_LONG_EDGE
        ) ?: return@runCatching null

        LoadedImage(
            previewBitmap = previewBitmap,
            analysisBitmap = analysisBitmap,
            format = detectFormat(resolver.getType(uri), metadata.displayName),
            originalWidth = bounds.width,
            originalHeight = bounds.height,
            analysisWidth = analysisBitmap.width,
            analysisHeight = analysisBitmap.height,
            fileSizeBytes = metadata.fileSizeBytes,
            downsampled = analysisBitmap.width != bounds.width || analysisBitmap.height != bounds.height
        )
    }.getOrNull()

    fun detectFormat(mimeType: String?, displayName: String?): String {
        val lowerMimeType = mimeType.orEmpty().lowercase()
        return when {
            lowerMimeType == "image/jpeg" || lowerMimeType == "image/jpg" -> "JPEG"
            lowerMimeType == "image/png" -> "PNG"
            lowerMimeType == "image/webp" -> "WebP"
            else -> detectFormatFromName(displayName)
        }
    }

    fun targetAnalysisSize(
        originalWidth: Int,
        originalHeight: Int,
        maxLongEdge: Int = ANALYSIS_LONG_EDGE
    ): BitmapSize = targetBitmapSize(originalWidth, originalHeight, maxLongEdge)

    fun calculateInSampleSize(
        originalWidth: Int,
        originalHeight: Int,
        maxLongEdge: Int = ANALYSIS_LONG_EDGE
    ): Int {
        require(originalWidth > 0) { "originalWidth must be positive" }
        require(originalHeight > 0) { "originalHeight must be positive" }
        require(maxLongEdge > 0) { "maxLongEdge must be positive" }

        var inSampleSize = 1
        val longEdge = max(originalWidth, originalHeight)
        while (longEdge / (inSampleSize * 2) >= maxLongEdge) {
            inSampleSize *= 2
        }
        return inSampleSize
    }

    @androidx.annotation.RequiresApi(Build.VERSION_CODES.P)
    private fun loadWithImageDecoder(
        resolver: ContentResolver,
        uri: Uri,
        metadata: SourceMetadata
    ): LoadedImage? {
        val preview = decodeBitmapForLongEdgeWithImageDecoder(resolver, uri, PREVIEW_LONG_EDGE)
            ?: return null
        val analysis = decodeBitmapForLongEdgeWithImageDecoder(resolver, uri, ANALYSIS_LONG_EDGE)
            ?: return null
        val originalWidth = preview.originalWidth
        val originalHeight = preview.originalHeight

        return LoadedImage(
            previewBitmap = preview.bitmap,
            analysisBitmap = analysis.bitmap,
            format = detectFormat(resolver.getType(uri), metadata.displayName),
            originalWidth = originalWidth,
            originalHeight = originalHeight,
            analysisWidth = analysis.bitmap.width,
            analysisHeight = analysis.bitmap.height,
            fileSizeBytes = metadata.fileSizeBytes,
            downsampled = analysis.bitmap.width != originalWidth || analysis.bitmap.height != originalHeight
        )
    }

    @androidx.annotation.RequiresApi(Build.VERSION_CODES.P)
    private fun decodeBitmapForLongEdgeWithImageDecoder(
        resolver: ContentResolver,
        uri: Uri,
        maxLongEdge: Int
    ): DecodedBitmap? = runCatching {
        var originalWidth = 0
        var originalHeight = 0
        val source = ImageDecoder.createSource(resolver, uri)
        val bitmap = ImageDecoder.decodeBitmap(source) { decoder, info, _ ->
            originalWidth = info.size.width
            originalHeight = info.size.height
            val target = targetBitmapSize(originalWidth, originalHeight, maxLongEdge)
            decoder.setTargetSize(target.width, target.height)
            decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
        }
        if (originalWidth <= 0 || originalHeight <= 0) {
            null
        } else {
            DecodedBitmap(bitmap = bitmap, originalWidth = originalWidth, originalHeight = originalHeight)
        }
    }.getOrNull()

    private fun detectFormatFromName(displayName: String?): String {
        val lowerName = displayName.orEmpty().lowercase()
        return when {
            lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") -> "JPEG"
            lowerName.endsWith(".png") -> "PNG"
            lowerName.endsWith(".webp") -> "WebP"
            else -> "Unknown"
        }
    }

    private fun targetBitmapSize(
        originalWidth: Int,
        originalHeight: Int,
        maxLongEdge: Int
    ): BitmapSize {
        require(originalWidth > 0) { "originalWidth must be positive" }
        require(originalHeight > 0) { "originalHeight must be positive" }
        require(maxLongEdge > 0) { "maxLongEdge must be positive" }

        val longEdge = max(originalWidth, originalHeight)
        if (longEdge <= maxLongEdge) {
            return BitmapSize(originalWidth, originalHeight, downsampled = false)
        }

        val scale = maxLongEdge.toDouble() / longEdge.toDouble()
        return BitmapSize(
            width = (originalWidth * scale).roundToInt().coerceAtLeast(1),
            height = (originalHeight * scale).roundToInt().coerceAtLeast(1),
            downsampled = true
        )
    }

    private fun readBounds(resolver: ContentResolver, uri: Uri): DecodedBounds? {
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        resolver.openInputStream(uri)?.use { stream ->
            BitmapFactory.decodeStream(stream, null, options)
        } ?: return null

        return DecodedBounds(width = options.outWidth, height = options.outHeight)
    }

    private fun decodeBitmapForLongEdge(
        resolver: ContentResolver,
        uri: Uri,
        originalWidth: Int,
        originalHeight: Int,
        maxLongEdge: Int
    ): Bitmap? {
        val options = BitmapFactory.Options().apply {
            inSampleSize = calculateInSampleSize(originalWidth, originalHeight, maxLongEdge)
            inPreferredConfig = Bitmap.Config.ARGB_8888
        }
        val decoded = resolver.openInputStream(uri)?.use { stream ->
            BitmapFactory.decodeStream(stream, null, options)
        } ?: return null

        val target = targetBitmapSize(originalWidth, originalHeight, maxLongEdge)
        if (decoded.width == target.width && decoded.height == target.height) {
            return decoded
        }

        return Bitmap.createScaledBitmap(decoded, target.width, target.height, true)
    }

    internal fun metadataOrFallback(queryMetadata: () -> SourceMetadata): SourceMetadata =
        runCatching(queryMetadata).getOrDefault(SourceMetadata(displayName = null, fileSizeBytes = null))

    private fun queryMetadata(resolver: ContentResolver, uri: Uri): SourceMetadata {
        resolver.query(
            uri,
            arrayOf(OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE),
            null,
            null,
            null
        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                val displayNameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                return SourceMetadata(
                    displayName = cursor.getNullableString(displayNameIndex),
                    fileSizeBytes = cursor.getNullableLong(sizeIndex)
                )
            }
        }
        return SourceMetadata(displayName = null, fileSizeBytes = null)
    }

    private fun android.database.Cursor.getNullableString(index: Int): String? =
        if (index >= 0 && !isNull(index)) getString(index) else null

    private fun android.database.Cursor.getNullableLong(index: Int): Long? =
        if (index >= 0 && !isNull(index)) getLong(index) else null

    data class SourceMetadata(
        val displayName: String?,
        val fileSizeBytes: Long?
    )

    private data class DecodedBounds(
        val width: Int,
        val height: Int
    )

    private data class DecodedBitmap(
        val bitmap: Bitmap,
        val originalWidth: Int,
        val originalHeight: Int
    )
}
