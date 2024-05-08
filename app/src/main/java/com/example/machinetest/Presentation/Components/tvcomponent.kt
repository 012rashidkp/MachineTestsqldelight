package com.example.machinetest.Presentation.Components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.machinetest.ui.theme.bggrey
import com.example.machinetest.ui.theme.black
import com.example.machinetest.ui.theme.popins_regular
import com.example.machinetest.ui.theme.tealcolor
import com.example.machinetest.ui.theme.whitecolor

@Composable
fun Tvcomponent(tvName:String?){
    Text(
        text = "${tvName}",
        color = whitecolor,
        fontSize = 19.sp, textAlign = TextAlign.Center,
        fontFamily = popins_regular
    )

}

@Composable
fun txtbutton(
    onClick: () -> Unit,
    tvName:String?
){
    TextButton(
        onClick = {
            onClick()
        }
    ) {
        Text(
            text = "${tvName}",
            color = tealcolor,
            fontSize = 16.sp, textAlign = TextAlign.Center,
            fontFamily = popins_regular
        )
    }


}
@Composable
fun TverrorText(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bggrey)
    ) {
        Text(
            text = "No data Found",
            color = black,
            fontSize = 18.sp,
            fontFamily = popins_regular,
            modifier = Modifier
                .align(Alignment.Center)

        )
    }




}