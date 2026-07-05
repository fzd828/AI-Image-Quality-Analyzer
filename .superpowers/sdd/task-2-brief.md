# Task 2: Implement Image Picker And Preview

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

## Controller Notes

Avoid adding Coil unless necessary. Prefer using Android platform APIs and Compose `Image` to keep dependencies minimal. If preview cannot be implemented without decode support, add a compact local URI-to-Bitmap loader inside the UI task, but keep metadata/downsampling for Task 3.

