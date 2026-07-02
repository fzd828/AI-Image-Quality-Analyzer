package com.example.imagequalityanalyzer.ui

import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory

class ImageAnalyzerScreenPreviewTest {
    @Test
    fun previewCardUsesWideAspectRatioAndBoundedHeight() {
        assertEquals(1.6f, PreviewUiDefaults.CardAspectRatio, 0.001f)
        assertEquals(280, PreviewUiDefaults.MaxCardHeightDp)
    }

    @Test
    fun appNameIsLocalizedForSystemPickerPrompts() {
        val stringsFile = locateStringsFile()
        val document = DocumentBuilderFactory.newInstance()
            .newDocumentBuilder()
            .parse(stringsFile.toFile())
        val appName = document.getElementsByTagName("string")
            .let { nodes ->
                (0 until nodes.length)
                    .map { nodes.item(it) }
                    .first { it.attributes.getNamedItem("name").nodeValue == "app_name" }
                    .textContent
            }

        assertEquals("画质分析器", appName)
    }

    private fun locateStringsFile(): Path {
        val candidates = listOf(
            Path.of("app/src/main/res/values/strings.xml"),
            Path.of("src/main/res/values/strings.xml"),
            Path.of("app/app/src/main/res/values/strings.xml")
        )

        return candidates.firstOrNull(Files::exists)
            ?: error("Could not locate strings.xml from ${Path.of("").toAbsolutePath()}")
    }
}
