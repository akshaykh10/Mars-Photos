package com.example.marsphotos.views

import android.os.CountDownTimer
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.marsphotos.LoadImage
import com.example.marsphotos.R
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.utils.PopUp
import com.example.marsphotos.viewModel.MarsApiStatus
import com.example.marsphotos.viewModel.OverViewModel

@ExperimentalMaterialApi
@Composable
fun MainScreen(
    viewModel: OverViewModel = viewModel()
){

    val status: MarsApiStatus by viewModel.status.observeAsState(MarsApiStatus.LOADING)
    val photos:List<MarsPhoto> by viewModel.photos.observeAsState(listOf())
    val navigateState = remember{ mutableStateOf(false) }

    BoxWithConstraints(
        modifier= Modifier.fillMaxSize().background(color= MaterialTheme.colors.background)
    ) {
        val rocketSize=250.dp
        val modifier:Modifier
        val infiniteTransition= rememberInfiniteTransition()
        val animationTargetState = remember { mutableStateOf(0f) }
        val finiteTransition =  animateFloatAsState(
            targetValue = animationTargetState.value,
            animationSpec = tween(durationMillis = 1500)
        )

        val horizontalPositionState=infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                tween(
                    durationMillis = 200,
                    easing = LinearEasing
                )
            )
        )

        when(status) {
            MarsApiStatus.DONE -> {
                animationTargetState.value = 1f
                object:CountDownTimer(1500,10){
                    override fun onTick(millisUntilFinished: Long) {
                        //Nothing
                    }
                    override fun onFinish() {
                        navigateState.value=true

                    }

                }.start()

                if(navigateState.value)
                {
                    Mars(photos)

                }

            }
            MarsApiStatus.ERROR -> {
                animationTargetState.value = 1f
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    object:CountDownTimer(1500,10){
                        override fun onTick(millisUntilFinished: Long) {
                            //Nothing
                        }
                        override fun onFinish() {
                            navigateState.value=true

                        }

                    }.start()

                    if(navigateState.value){

                        Column(
                            modifier=Modifier.fillMaxSize()
                                .padding(16.dp)
                                .offset(y=(-80).dp),
                            verticalArrangement = Arrangement.Center

                        ){
                            Box(
                                modifier=Modifier
                                    .clip(shape= RoundedCornerShape(24.dp))
                                    .padding(40.dp)
                                    .fillMaxWidth()
                                    .border(width=2.dp,color=MaterialTheme.colors.onBackground,shape=RoundedCornerShape(24.dp)),
                                contentAlignment = Alignment.Center,

                                ){
                                Text(
                                    text = "Check your Internet Connection and Relaunch...",
                                    style=MaterialTheme.typography.subtitle1,
                                    modifier=Modifier.padding(24.dp),
                                    color = MaterialTheme.colors.onBackground
                                )

                            }
                            

                            Image(
                                painter = painterResource(id = if (isSystemInDarkTheme()) R.drawable.ic_final_rover_dark_mode else R.drawable.ic_final_rover),
                                contentDescription = null,
                                modifier = Modifier.size(260.dp),
                                contentScale = ContentScale.Fit
                            )

                        }

                    }
                }

            }
        }

        modifier=Modifier.offset(
            x=(((maxWidth-rocketSize)/2)-2.dp)+  (horizontalPositionState.value * 4).dp ,
            y=(maxHeight-rocketSize) -(maxHeight * finiteTransition.value)
        )

        Image(
            painter = painterResource(id= R.drawable.ic_rocket_svg),
            modifier=modifier.width(rocketSize).height(rocketSize),
            contentDescription = null
        )
    }

}

@ExperimentalMaterialApi
@Composable
fun Mars(photos:List<MarsPhoto>){
    val popUpEnabled = remember { mutableStateOf(false) }
    val dismissRequest ={ popUpEnabled.value=false}
    val titleColor by animateColorAsState(if(popUpEnabled.value) MaterialTheme.colors.onBackground.copy(alpha=0.6f) else MaterialTheme.colors.onBackground)

    Surface(
        modifier=Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier=Modifier.fillMaxSize().padding(start=16.dp,end=16.dp,top=24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier=Modifier.fillMaxWidth(),
                horizontalArrangement =Arrangement.Center

            ){
                Text(
                    text= stringResource(id = R.string.title),
                    style = MaterialTheme.typography.h1,
                    color =titleColor

                )
                Spacer(modifier=Modifier.width(6.dp))
                Card(
                    onClick={ popUpEnabled.value=true },
                    shape= CircleShape,
                    elevation=4.dp,
                    enabled=!popUpEnabled.value

                ){
                    Image(
                        painterResource(R.drawable.dogecoin_logo),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier=Modifier.size(48.dp).clip(shape= CircleShape),

                    )

                }

            }

            Spacer(modifier=Modifier.height(24.dp))

            LazyColumn(
                modifier=Modifier.fillMaxSize()
            ) {
                items( photos){
                    LoadImage(it.imgSrcUrl)
                    Spacer(modifier=Modifier.height(12.dp))

                }
            }

        }

    }

    if(popUpEnabled.value)
    {
        PopUp(popUpEnabled.value,dismissRequest)
    }


}



