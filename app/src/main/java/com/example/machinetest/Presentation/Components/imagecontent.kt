package com.example.machinetest.Presentation.Components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.machinetest.ui.theme.imagepicker


@Composable
fun imagecontent(uri: Uri?){
    val pickimage= painterResource(id = imagepicker)
    if (uri!=null){
        AsyncImage(
            model = uri,
            contentDescription = "",
            modifier = Modifier
                .width(65.dp)
                .height(65.dp),
            contentScale = ContentScale.Crop
        )
    }
    else{
        Image(
            painter = pickimage, contentDescription =null,
            modifier = Modifier
                .size(65.dp)

        )
    }
}