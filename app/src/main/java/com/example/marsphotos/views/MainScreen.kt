package com.example.marsphotos.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marsphotos.LoadImage
import com.example.marsphotos.R
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.theme.mars
import com.example.marsphotos.viewModel.MarsApiStatus
import com.example.marsphotos.viewModel.OverViewModel


@Composable
fun MainScreen(viewModel: OverViewModel = viewModel()){

    val status: MarsApiStatus by viewModel.status.observeAsState(MarsApiStatus.LOADING)

    val photos:List<MarsPhoto> by viewModel.photos.observeAsState(listOf())

    Surface(
        modifier=Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier=Modifier.fillMaxSize().padding(start=16.dp,end=16.dp,top=24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text= stringResource(id = R.string.title),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier=Modifier.height(24.dp))

            when (status){
                MarsApiStatus.LOADING -> {
                    Box(
                        modifier=Modifier.fillMaxSize()
                    ){
                        CircularProgressIndicator(
                            modifier= Modifier.align(Alignment.Center),
                            color= mars,
                            strokeWidth = 2.dp,

                            )
                    }

                }

                MarsApiStatus.DONE -> {
                    LazyColumn(
                        modifier=Modifier.fillMaxSize()
                    ) {
                        items(photos){
                            LoadImage(it.imgSrcUrl)
                            Spacer(modifier=Modifier.height(8.dp))

                        }
                    }
                }

                MarsApiStatus.ERROR -> {
                    Box(
                        modifier=Modifier.fillMaxSize()
                    ){
                        Image(
                            painter = painterResource(id =  if(isSystemInDarkTheme()) R.drawable.ic_final_rover_dark_mode else R.drawable.ic_final_rover),
                            contentDescription = null,
                            modifier= Modifier.align(Alignment.Center)
                                .size(260.dp),
                            contentScale = ContentScale.Fit


                        )
                    }


                }

            }




        }

    }

}