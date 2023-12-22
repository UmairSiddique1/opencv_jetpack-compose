package com.example.opencvjetpackcompose

import android.util.Log
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CameraLayout(previewView:PreviewView,onIconClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        AndroidView({ previewView}, modifier = Modifier.fillMaxSize())
        Row(modifier = Modifier
            .padding(5.dp).align(Alignment.TopCenter)) {
            Text("Manual", color = Color.White, modifier = Modifier.padding(5.dp))
Icon(painter = painterResource(id = R.drawable.ic_cameraarrow), contentDescription =null, tint = Color.White, modifier = Modifier.padding(5.dp) )}
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd){
                Icon(painter = painterResource(id = R.drawable.ic_camera_grid), contentDescription =null, tint = Color.White, modifier = Modifier.padding(5.dp))
            }




        Row (modifier = Modifier.align(Alignment.BottomStart)){
            Icon(painter = painterResource(id = R.drawable.ic_cameracross), contentDescription = null, tint = Color.White,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .padding(end = 15.dp)
                    .padding(bottom = 30.dp)
                    .align(Alignment.CenterVertically))
            Icon(painterResource(id = R.drawable.ic_cameragallery), contentDescription =null, tint = Color.White
                , modifier = Modifier
                    .padding(start = 15.dp)
                    .padding(end = 15.dp)
                    .padding(bottom = 30.dp)
                    .align(Alignment.CenterVertically))
        }
        IconButton(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .padding(start = 10.dp)
                .padding(end = 10.dp)
                .align(Alignment.BottomCenter),
            onClick = {
                Log.i("kilo", "ON CLICK")
                onIconClick()
            },
            content = {
                Icon(
                    painter = painterResource(R.drawable.ic_cameracapture),
                    contentDescription = "Take picture",
                    tint = Color.White,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(1.dp)
                        .border(1.dp, Color.White, CircleShape)
                        .align(Alignment.Center))
            })
    }
}
