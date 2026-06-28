package com.example.imagequalityanalyzer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.imagequalityanalyzer.ui.ImageAnalyzerScreen
import com.example.imagequalityanalyzer.ui.theme.ImageQualityAnalyzerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageQualityAnalyzerTheme {
                ImageAnalyzerScreen()
            }
        }
    }
}
