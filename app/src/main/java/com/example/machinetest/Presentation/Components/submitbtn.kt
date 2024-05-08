package com.example.machinetest.Presentation.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.machinetest.ui.theme.tealcolor
import com.example.machinetest.ui.theme.whitecolor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Submitbutton(
    onClick: () -> Unit,
    loading:Boolean,
    enabled:Boolean?=true,
    buttontxt:String?
){
    val controller= LocalSoftwareKeyboardController.current



    Button(
        onClick = { onClick() },
        enabled = enabled?:true,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 25.dp),

        shape = RoundedCornerShape(12.dp),

        colors = ButtonDefaults.buttonColors(containerColor = tealcolor),
    ) {
        if (loading){
            controller?.hide()
            Loadingbar()
        }
        else{
            Tvcomponent(tvName = buttontxt)
        }


    }
}
@Composable
fun Createbutton(
    onClick: () -> Unit,
    loading:Boolean?=false,
    buttontxt:String?,

    ){
    ElevatedButton(
        onClick = { onClick() },
        Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(tealcolor)




    ) {
        if (loading?:false){
            Loadingbar()
        }
        else{
            Icon(
                imageVector = Icons.Outlined.Add ,
                contentDescription =null,
                modifier = Modifier.size(23.dp),
                tint = whitecolor

            )
            Tvcomponent(tvName = buttontxt)
        }


    }
}

@Composable
fun Loadingbar(){
    CircularProgressIndicator(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        color = Color.White

    )
}