package com.example.imagequalityanalyzer.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.ui.res.stringResource
import com.example.imagequalityanalyzer.R
import com.example.imagequalityanalyzer.analysis.ImageQualityAnalyzer
import com.example.imagequalityanalyzer.analysis.ImageQualityScorer
import com.example.imagequalityanalyzer.analysis.QualityResult
import com.example.imagequalityanalyzer.image.AnalysisMode
import com.example.imagequalityanalyzer.image.ImageLoader
import com.example.imagequalityanalyzer.image.LoadedImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

@Composable
fun ImageAnalyzerScreen() {
    var selectedUri by remember { mutableStateOf<Uri?>(null) }
    var analysisMode by remember { mutableStateOf(AnalysisMode.Fast) }
    var analysisRequested by remember { mutableStateOf(false) }
    var fullScreenPreviewImage by remember { mutableStateOf<LoadedImage?>(null) }
    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedUri = uri
            analysisRequested = false
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .then(if (fullScreenPreviewImage != null) Modifier.blur(8.dp) else Modifier)
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Header()
                AnalysisModeSection(
                    selectedMode = analysisMode,
                    onModeSelected = { mode ->
                        analysisMode = mode
                        analysisRequested = false
                    }
                )

                if (selectedUri == null) {
                    EmptyState(onSelectImage = { picker.launch("image/*") })
                } else {
                    SelectedImagePreview(
                        uri = checkNotNull(selectedUri),
                        analysisMode = analysisMode,
                        analysisRequested = analysisRequested,
                        onReselect = { picker.launch("image/*") },
                        onStartAnalysis = { analysisRequested = true },
                        onOpenPreview = { image -> fullScreenPreviewImage = image }
                    )
                }
            }

            fullScreenPreviewImage?.let { image ->
                FullScreenImageViewer(
                    image = image,
                    onDismiss = { fullScreenPreviewImage = null },
                    modifier = Modifier.zIndex(1f)
                )
            }
        }
    }
}

@Composable
private fun Header() {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = stringResource(R.string.home_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = stringResource(R.string.home_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AnalysisModeSection(
    selectedMode: AnalysisMode,
    onModeSelected: (AnalysisMode) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SectionTitle("分析模式")
            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                AnalysisMode.entries.forEachIndexed { index, mode ->
                    SegmentedButton(
                        selected = selectedMode == mode,
                        onClick = { onModeSelected(mode) },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = AnalysisMode.entries.size
                        ),
                        label = {
                            Text(
                                text = "${mode.displayName} ${mode.maxLongEdge}",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyState(onSelectImage: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionTitle("图片预览")
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val previewHeight = (maxWidth / PreviewUiDefaults.CardAspectRatio)
                    .coerceAtMost(PreviewUiDefaults.MaxCardHeightDp.dp)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(previewHeight)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "尚未选择图片",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Button(
                onClick = onSelectImage,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("选择图片")
            }
        }
    }
}

@Composable
private fun SelectedImagePreview(
    uri: Uri,
    analysisMode: AnalysisMode,
    analysisRequested: Boolean,
    onReselect: () -> Unit,
    onStartAnalysis: () -> Unit,
    onOpenPreview: (LoadedImage) -> Unit
) {
    val context = LocalContext.current
    val loadState by produceState<ImageLoadState>(
        initialValue = ImageLoadState.Loading,
        uri,
        analysisMode
    ) {
        value = withContext(Dispatchers.IO) {
            ImageLoader.load(context, uri, analysisMode)?.let(ImageLoadState::Loaded)
                ?: ImageLoadState.Error
        }
    }

    when (val state = loadState) {
        ImageLoadState.Loading -> StatusCard("正在加载图片...")
        ImageLoadState.Error -> StatusCard("无法加载图片")
        is ImageLoadState.Loaded -> LoadedImagePreview(
            image = state.image,
            analysisRequested = analysisRequested,
            onReselect = onReselect,
            onStartAnalysis = onStartAnalysis,
            onOpenPreview = onOpenPreview
        )
    }
}

@Composable
private fun LoadedImagePreview(
    image: LoadedImage,
    analysisRequested: Boolean,
    onReselect: () -> Unit,
    onStartAnalysis: () -> Unit,
    onOpenPreview: (LoadedImage) -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PreviewSectionTitle()
            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val previewHeight = (maxWidth / PreviewUiDefaults.CardAspectRatio)
                    .coerceAtMost(PreviewUiDefaults.MaxCardHeightDp.dp)
                Image(
                    bitmap = image.previewBitmap.asImageBitmap(),
                    contentDescription = "已选择的图片预览",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(previewHeight)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .clickable { onOpenPreview(image) },
                    contentScale = ContentScale.Fit
                )
            }
            MetadataGrid(
                rows = listOf(
                    "格式" to image.format,
                    "原始尺寸" to "${image.originalWidth} x ${image.originalHeight}",
                    "文件大小" to image.fileSizeBytes?.let(::formatFileSize).orEmpty().ifBlank { "未知" }
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onReselect,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("重新选择")
                }
                Button(
                    onClick = onStartAnalysis,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("开始分析")
                }
            }
        }
    }

    if (analysisRequested) {
        AnalysisResult(image)
    }
}

@Composable
private fun FullScreenImageViewer(
    image: LoadedImage,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var scale by remember(image) { mutableStateOf(1f) }
    var offsetX by remember(image) { mutableStateOf(0f) }
    var offsetY by remember(image) { mutableStateOf(0f) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.88f))
            .pointerInput(onDismiss) {
                detectTapGestures(onTap = { onDismiss() })
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = image.previewBitmap.asImageBitmap(),
            contentDescription = "放大的图片预览",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offsetX
                    translationY = offsetY
                }
                .pointerInput(image) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        val nextScale = (scale * zoom).coerceIn(
                            1f,
                            PreviewUiDefaults.ViewerMaxScale
                        )
                        scale = nextScale
                        if (nextScale == 1f) {
                            offsetX = 0f
                            offsetY = 0f
                        } else {
                            offsetX += pan.x
                            offsetY += pan.y
                        }
                    }
                },
            contentScale = ContentScale.Fit
        )
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
        AnalysisState.Analyzing -> StatusCard("正在分析图片...")
        is AnalysisState.Analyzed -> ResultCard(
            image = image,
            result = state.result,
            elapsedMillis = state.elapsedMillis
        )
    }
}

@Composable
private fun ResultCard(
    image: LoadedImage,
    result: QualityResult,
    elapsedMillis: Long
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SectionTitle("分析结果")
            MetadataGrid(
                rows = listOf(
                    "分析模式" to "${image.analysisMode.displayName}（最长边 ${image.analysisMode.maxLongEdge}）",
                    "分析尺寸" to "${image.analysisWidth} x ${image.analysisHeight}",
                    "大图安全处理" to if (image.downsampled) "已安全缩放" else "原尺寸已在安全范围",
                    "分析耗时" to "$elapsedMillis ms"
                )
            )
            ScoreSummary(result.overallScore)
            ScoreRow(label = "清晰度", score = result.sharpnessScore)
            ScoreRow(label = "曝光", score = result.exposureScore)
            ScoreRow(label = "对比度", score = result.contrastScore)
            ScoreRow(label = "偏色", score = result.colorCastScore)
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "诊断",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = result.explanation,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun ScoreSummary(overallScore: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primaryContainer,
                        MaterialTheme.colorScheme.tertiaryContainer
                    )
                )
            )
            .padding(18.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = "综合评分",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "$overallScore/100",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun ScoreRow(label: String, score: Int) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "$score/100",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        LinearProgressIndicator(
            progress = { score / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            color = scoreColor(score),
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Composable
private fun MetadataGrid(rows: List<Pair<String, String>>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        rows.forEach { (label, value) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = value,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.End,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun PreviewSectionTitle() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SectionTitle("图片预览")
        Text(
            text = "点击图片可放大查看",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
private fun StatusCard(message: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
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

@Composable
private fun scoreColor(score: Int): Color =
    when {
        score >= 80 -> MaterialTheme.colorScheme.primary
        score >= 60 -> MaterialTheme.colorScheme.tertiary
        else -> MaterialTheme.colorScheme.error
    }

private fun formatFileSize(bytes: Long): String =
    when {
        bytes >= 1024L * 1024L -> "%.1f MB".format(bytes / (1024.0 * 1024.0))
        bytes >= 1024L -> "%.1f KB".format(bytes / 1024.0)
        else -> "$bytes B"
    }

internal object PreviewUiDefaults {
    const val CardAspectRatio = 1.6f
    const val MaxCardHeightDp = 280
    const val ViewerMaxScale = 5f
}
