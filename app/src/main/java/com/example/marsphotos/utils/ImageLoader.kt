package com.example.marsphotos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.marsphotos.ui.theme.mars
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState




@Composable
fun LoadImage(url:String){
    val painter= rememberCoilPainter(
        request=url,
        fadeIn=true
    )

    Box (modifier=Modifier.clip(shape=MaterialTheme.shapes.large)) {
        Image(
            painter =painter,
            contentDescription = null,
            modifier= Modifier.fillMaxWidth()
                .height(196.dp)
                .clip(shape= MaterialTheme.shapes.large),
            contentScale = ContentScale.FillBounds
        )


        when (painter.loadState) {
            is ImageLoadState.Loading -> {
                // Display a circular progress indicator whilst loading
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center),
                    color = mars,
                    strokeWidth = 1.dp
                )
            }
            is ImageLoadState.Error -> {
               Icon(
                   painter=painterResource(R.drawable.ic_baseline_error_24),
                   contentDescription = null,
                   modifier=Modifier.align(Alignment.Center),
                   tint=mars

               )
            }
        }
    }
}