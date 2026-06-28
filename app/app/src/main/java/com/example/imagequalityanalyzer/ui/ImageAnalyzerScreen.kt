package com.example.imagequalityanalyzer.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.imagequalityanalyzer.image.ImageLoader
import com.example.imagequalityanalyzer.image.LoadedImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { picker.launch("image/*") }) {
            Text("Select image")
        }
        Spacer(modifier = Modifier.height(16.dp))
        SelectedImagePreview(selectedUri)
    }
}

@Composable
private fun SelectedImagePreview(uri: Uri?) {
    if (uri == null) {
        Text("No image selected")
        return
    }

    val context = LocalContext.current
    val loadState by produceState<ImageLoadState>(initialValue = ImageLoadState.Loading, uri) {
        value = withContext(Dispatchers.IO) {
            ImageLoader.load(context, uri)?.let(ImageLoadState::Loaded) ?: ImageLoadState.Error
        }
    }

    when (val state = loadState) {
        ImageLoadState.Loading -> Text("Loading image...")
        ImageLoadState.Error -> Text("Unable to load image")
        is ImageLoadState.Loaded -> LoadedImagePreview(state.image)
    }
}

@Composable
private fun LoadedImagePreview(image: LoadedImage) {
    Column {
        Image(
            bitmap = image.previewBitmap.asImageBitmap(),
            contentDescription = "Selected image",
            modifier = Modifier.fillMaxWidth().height(280.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Format: ${image.format}")
        Text("Original: ${image.originalWidth} x ${image.originalHeight}")
        Text("Analysis: ${image.analysisWidth} x ${image.analysisHeight}")
        image.fileSizeBytes?.let { size ->
            Text("File size: ${formatFileSize(size)}")
        }
        Text("Downsampled for analysis: ${if (image.downsampled) "Yes" else "No"}")
    }
}

private sealed interface ImageLoadState {
    data object Loading : ImageLoadState
    data object Error : ImageLoadState
    data class Loaded(val image: LoadedImage) : ImageLoadState
}

private fun formatFileSize(bytes: Long): String =
    when {
        bytes >= 1024L * 1024L -> "%.1f MB".format(bytes / (1024.0 * 1024.0))
        bytes >= 1024L -> "%.1f KB".format(bytes / 1024.0)
        else -> "$bytes B"
    }
