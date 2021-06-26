package com.example.marsphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.example.marsphotos.ui.theme.MarsPhotosTheme
import com.example.marsphotos.views.MainScreen

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarsPhotosTheme {
                MainScreen ()
            }
        }
    }
}








