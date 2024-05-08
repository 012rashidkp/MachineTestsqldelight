package com.example.machinetest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.machinetest.Presentation.Components.MyTopbar
import com.example.machinetest.Presentation.Components.TverrorText
import com.example.machinetest.Presentation.Components.cartcomponent
import com.example.machinetest.Presentation.Components.totalpricecomponent
import com.example.machinetest.ViewModel.CartViewModel
import com.example.machinetest.ui.theme.bggrey
import com.example.machinetest.ui.theme.black
import com.example.machinetest.ui.theme.greylight
import com.example.machinetest.ui.theme.popins_regular
import com.example.machinetest.ui.theme.whitecolor

@Composable
fun CartScreen(navController: NavController){
    val cartViewModel= hiltViewModel<CartViewModel>()
     
    val getAllCartinfo  by remember (cartViewModel) {cartViewModel.cartAllinfo}.collectAsState(initial = emptyList())
    
    
    
    
   Scaffold(
       modifier = Modifier
           .fillMaxSize()
           .background(whitecolor),
       topBar = {
          MyTopbar(
              title = "Cart",
              onClick = {
               cartViewModel.deleteAllfromcart()

              }
              )
       },
       bottomBar = {
            if (getAllCartinfo.isNotEmpty()) totalpricecomponent(priceitems = getAllCartinfo)

       }


   ) {paddingValues ->
       Box(
           modifier = Modifier
               .fillMaxSize()
               .padding(paddingValues)
               .background(bggrey)
       ) {

           Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(vertical = 10.dp, horizontal = 16.dp),
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               if (getAllCartinfo.isEmpty()){
                  TverrorText()
               }
               else{

                   cartcomponent(
                       items = getAllCartinfo,
                       qtyclick = {cartid, qty, subtotal,productid ->
                           val mysubtotal= subtotal?.times(qty!!)
                           cartViewModel.updatecart(cart_id = cartid,qty=qty, subtotal =mysubtotal, product_id = productid)
                       },
                       deleteclick = {cart_id ->
                           cartViewModel.deleteitem(cart_id)
                       }
                   )



               }


               
               
               
               
           }
         
           
           
           
           
       }
   }



}