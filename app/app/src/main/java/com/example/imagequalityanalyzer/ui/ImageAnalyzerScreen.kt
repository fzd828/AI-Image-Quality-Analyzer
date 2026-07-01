package com.example.imagequalityanalyzer.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.imagequalityanalyzer.analysis.ImageQualityAnalyzer
import com.example.imagequalityanalyzer.analysis.ImageQualityScorer
import com.example.imagequalityanalyzer.analysis.QualityResult
import com.example.imagequalityanalyzer.image.ImageLoader
import com.example.imagequalityanalyzer.image.LoadedImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

@Composable
fun ImageAnalyzerScreen() {
    var selectedUri by remember { mutableStateOf<Uri?>(null) }
    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedUri = uri
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Button(onClick = { picker.launch("image/*") }) {
            Text("选择图片")
        }
        Spacer(modifier = Modifier.height(16.dp))
        SelectedImagePreview(selectedUri)
    }
}

@Composable
private fun SelectedImagePreview(uri: Uri?) {
    if (uri == null) {
        Text("尚未选择图片")
        return
    }

    val context = LocalContext.current
    val loadState by produceState<ImageLoadState>(initialValue = ImageLoadState.Loading, uri) {
        value = withContext(Dispatchers.IO) {
            ImageLoader.load(context, uri)?.let(ImageLoadState::Loaded) ?: ImageLoadState.Error
        }
    }

    when (val state = loadState) {
        ImageLoadState.Loading -> Text("正在加载图片...")
        ImageLoadState.Error -> Text("无法加载图片")
        is ImageLoadState.Loaded -> LoadedImagePreview(state.image)
    }
}

@Composable
private fun LoadedImagePreview(image: LoadedImage) {
    Column {
        Image(
            bitmap = image.previewBitmap.asImageBitmap(),
            contentDescription = "已选择的图片",
            modifier = Modifier.fillMaxWidth().height(280.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("格式：${image.format}")
        Text("原始尺寸：${image.originalWidth} x ${image.originalHeight}")
        Text("分析尺寸：${image.analysisWidth} x ${image.analysisHeight}")
        image.fileSizeBytes?.let { size ->
            Text("文件大小：${formatFileSize(size)}")
        }
        Text("分析时已降采样：${if (image.downsampled) "是" else "否"}")
        Spacer(modifier = Modifier.height(16.dp))
        AnalysisResult(image)
    }
}

@Composable
private fun AnalysisResult(image: LoadedImage) {
    val analysisState by produceState<AnalysisState>(
        initialValue = AnalysisState.Analyzing,
        image
    ) {
        value = withContext(Dispatchers.Default) {
            analyzeImage(image)
        }
    }

    when (val state = analysisState) {
        AnalysisState.Analyzing -> Text("正在分析图片...")
        is AnalysisState.Analyzed -> {
            Text("综合评分：${state.result.overallScore}/100")
            Text("分析耗时：${state.elapsedMillis} ms")
            Text("诊断：${state.result.explanation}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("清晰度：${state.result.sharpnessScore}/100")
            Text("曝光：${state.result.exposureScore}/100")
            Text("对比度：${state.result.contrastScore}/100")
            Text("偏色：${state.result.colorCastScore}/100")
        }
    }
}

private fun analyzeImage(image: LoadedImage): AnalysisState.Analyzed {
    val bitmap = image.analysisBitmap
    val pixels = IntArray(bitmap.width * bitmap.height)
    var result: QualityResult? = null
    val elapsedMillis = measureTimeMillis {
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        val metrics = ImageQualityAnalyzer.analyze(
            width = bitmap.width,
            height = bitmap.height,
            argbPixels = pixels
        )
        result = ImageQualityScorer.score(metrics)
    }

    return AnalysisState.Analyzed(
        result = checkNotNull(result),
        elapsedMillis = elapsedMillis
    )
}

private sealed interface ImageLoadState {
    data object Loading : ImageLoadState
    data object Error : ImageLoadState
    data class Loaded(val image: LoadedImage) : ImageLoadState
}

private sealed interface AnalysisState {
    data object Analyzing : AnalysisState
    data class Analyzed(
        val result: QualityResult,
        val elapsedMillis: Long
    ) : AnalysisState
}

private fun formatFileSize(bytes: Long): String =
    when {
        bytes >= 1024L * 1024L -> "%.1f MB".format(bytes / (1024.0 * 1024.0))
        bytes >= 1024L -> "%.1f KB".format(bytes / 1024.0)
        else -> "$bytes B"
    }
