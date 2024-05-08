package com.example.machinetest.Presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.machinetest.ui.theme.addicon
import com.example.machinetest.ui.theme.popins_regular
import com.example.machinetest.ui.theme.tealcolor
import com.example.machinetest.ui.theme.whitecolor
import com.example.machinetest.utility.getNetTotal
import productdb.GetAllCartinfo

@Composable
fun MyfloatingActionButton(onClick: () -> Unit,){
    FloatingActionButton(

        onClick = {
            // navController.navigate(Screens.CreatePage.route)
                  onClick()
        },
        contentColor = tealcolor,
        containerColor = tealcolor,
        elevation = FloatingActionButtonDefaults.elevation(2.dp),
        shape = FloatingActionButtonDefaults.shape,

        content = {
            Icon(
                painter = painterResource(id = addicon),
                contentDescription = null,
                tint = whitecolor
            )
        }
    )


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopbar(
    title:String?,
    number:String?="",
    needaction:Boolean?=false,
    onClick: () -> Unit
){
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = tealcolor
        ),
        title = {
            Text(
                text = title?:"",
                color = whitecolor,
                fontSize = 18.sp,
                fontFamily = popins_regular,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center


            )
        },
        // 2

        actions = {
if (needaction?:false){
    MyBadgebox(
        number = number?:"",
        onClick={
            onClick()
        }
    )
}
else{
    IconButton(
        onClick = {
           onClick()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            tint = whitecolor,
            contentDescription = "Delete",
        )
    }
}










        },

        )
}

@Composable
fun MyBadgebox(
    number:String,
    onClick: () -> Unit
    ){
    BadgedBox(
        modifier = Modifier
            .clickable {

            },
        badge = { Badge { Text(number,
            modifier = Modifier
                .padding(5.dp)

            ) } }
    ) {
        IconButton(
          modifier = Modifier.size(45.dp),
         onClick={
             onClick()
         }
        ){
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                tint = whitecolor,
                contentDescription = "Cart",
            )
        }
    }


     }

@Composable
fun totalpricecomponent(priceitems:List<GetAllCartinfo>){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(tealcolor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text ="Order Total ${getNetTotal(priceitems)}",
            color = whitecolor,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,


        )

    }

}