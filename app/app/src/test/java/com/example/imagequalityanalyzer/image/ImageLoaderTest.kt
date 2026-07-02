package com.example.imagequalityanalyzer.image

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ImageLoaderTest {
    @Test
    fun detectsRequiredFormatsFromMimeTypeOrFileName() {
        assertEquals("JPEG", ImageLoader.detectFormat("image/jpeg", null))
        assertEquals("PNG", ImageLoader.detectFormat(null, "sample.PNG"))
        assertEquals("WebP", ImageLoader.detectFormat("image/webp", "ignored.jpg"))
        assertEquals("Unknown", ImageLoader.detectFormat("application/octet-stream", "notes.txt"))
    }

    @Test
    fun targetAnalysisSizeKeepsSmallImagesAtOriginalDimensions() {
        val size = ImageLoader.targetAnalysisSize(originalWidth = 640, originalHeight = 480)

        assertEquals(640, size.width)
        assertEquals(480, size.height)
        assertFalse(size.downsampled)
    }

    @Test
    fun targetAnalysisSizeKeepsAspectRatioWithLongEdgeNear1280() {
        val size = ImageLoader.targetAnalysisSize(originalWidth = 4000, originalHeight = 3000)

        assertEquals(1280, size.width)
        assertEquals(960, size.height)
        assertTrue(size.downsampled)
    }

    @Test
    fun targetAnalysisSizeUsesFastModeLongEdge1280() {
        val size = ImageLoader.targetAnalysisSize(
            originalWidth = 4000,
            originalHeight = 3000,
            mode = AnalysisMode.Fast
        )

        assertEquals(1280, size.width)
        assertEquals(960, size.height)
        assertTrue(size.downsampled)
    }

    @Test
    fun targetAnalysisSizeUsesDetailedModeLongEdge2560() {
        val size = ImageLoader.targetAnalysisSize(
            originalWidth = 4000,
            originalHeight = 3000,
            mode = AnalysisMode.Detailed
        )

        assertEquals(2560, size.width)
        assertEquals(1920, size.height)
        assertTrue(size.downsampled)
    }

    @Test
    fun sampleSizeChoosesSafePowerOfTwoDecodeFloor() {
        assertEquals(1, ImageLoader.calculateInSampleSize(originalWidth = 1280, originalHeight = 960))
        assertEquals(2, ImageLoader.calculateInSampleSize(originalWidth = 4000, originalHeight = 3000))
        assertEquals(8, ImageLoader.calculateInSampleSize(originalWidth = 12000, originalHeight = 9000))
    }

    @Test
    fun metadataQueryFailureFallsBackToUnknownMetadata() {
        val metadata = ImageLoader.metadataOrFallback {
            error("Photo Picker provider does not expose OpenableColumns")
        }

        assertEquals(null, metadata.displayName)
        assertEquals(null, metadata.fileSizeBytes)
    }
}
