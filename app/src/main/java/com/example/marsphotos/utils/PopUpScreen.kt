package com.example.marsphotos.utils

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.marsphotos.R
import com.example.marsphotos.ui.theme.black
import com.example.marsphotos.ui.theme.white


@ExperimentalMaterialApi
@Composable
fun PopUp(enabled:Boolean,onDismissRequest: ()-> Unit){

    if(enabled){
        Popup (
            onDismissRequest = onDismissRequest,
            content = { PopUpContent (onClick = onDismissRequest) },
            offset = IntOffset(x=0,y=200)
        )
    }

}

@ExperimentalMaterialApi
@Composable
fun PopUpContent(onClick:()->Unit){
    val context=LocalContext.current
    val intent=remember{ Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/akshaykh10/Mars-Photos")) }
    Box(
        modifier= Modifier.fillMaxSize()
            .background(color= MaterialTheme.colors.background.copy(alpha=0.4f))
            .padding(16.dp),

    ){
        Card(
            modifier=Modifier.size(160.dp)
                .shadow(elevation=24.dp,shape = RoundedCornerShape(48.dp))
                .align(Alignment.Center),
            shape = MaterialTheme.shapes.large,
            backgroundColor = white,
            onClick={ context.startActivity(intent)}
        ){
            Image(
                painter=painterResource(R.drawable.github),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier=Modifier.fillMaxSize().border(width=2.dp,color= black)
            )


        }

        IconButton(
            onClick=onClick,
            content= { if(isSystemInDarkTheme()) CloseIcon(white) else CloseIcon(black) }  ,
            modifier=Modifier.align(Alignment.TopStart).offset( x=0.dp,y=32.dp),

        )
    }
}