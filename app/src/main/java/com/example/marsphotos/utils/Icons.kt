package com.example.marsphotos.utils

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.marsphotos.R

@Composable
fun CloseIcon(color: Color){
    Icon(
        painter= painterResource(R.drawable.ic_baseline_close_24),
        contentDescription = null,
        tint= color
    )
}





