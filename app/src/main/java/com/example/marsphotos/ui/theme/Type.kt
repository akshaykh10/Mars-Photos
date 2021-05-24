package com.example.marsphotos.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.marsphotos.R

val zenDot_regular= FontFamily(Font(R.font.zendots_regular))
// Set of Material typography styles to start with
val typography = Typography(
    h1 = TextStyle(
        fontFamily = zenDot_regular,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        letterSpacing = 0.15.sp
    )

)