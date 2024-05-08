package com.example.machinetest.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.machinetest.Domain.Model.Categories
import com.example.machinetest.Navigation.Screens
import com.example.machinetest.Presentation.Components.CategorylistRow
import com.example.machinetest.Presentation.Components.MyTopbar
import com.example.machinetest.Presentation.Components.MyfloatingActionButton
import com.example.machinetest.Presentation.Components.TverrorText
import com.example.machinetest.Presentation.Components.productComponent
import com.example.machinetest.ViewModel.CartViewModel
import com.example.machinetest.ViewModel.ProductViewModel
import com.example.machinetest.ui.theme.AnimatedSplashScreenTheme
import com.example.machinetest.ui.theme.addicon
import com.example.machinetest.ui.theme.bggrey
import com.example.machinetest.ui.theme.black
import com.example.machinetest.ui.theme.greylight
import com.example.machinetest.ui.theme.popins_regular
import com.example.machinetest.ui.theme.tealcolor
import com.example.machinetest.ui.theme.whitecolor
import com.example.machinetest.utility.categoryitems
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController){
    val pviewModel= hiltViewModel<ProductViewModel>()
    val cartViewModel= hiltViewModel<CartViewModel>()
    val products by remember (pviewModel) {pviewModel.products}.collectAsState(initial = emptyList())

    val updatedList = mutableListOf<Categories>()
    for (i in 0 until categoryitems.size){
        updatedList.add(Categories(categoryitems.get(i).id, categoryitems.get(i).catName))
    }
    updatedList.add(0, Categories(0,"All"))
    var category_id by remember { mutableStateOf(0L) }


    val productsbycat=pviewModel.getproductbycat(category_id).collectAsState(initial = emptyList()).value
    val scope= rememberCoroutineScope()
    val context= LocalContext.current

    val getcartcount by remember (cartViewModel){cartViewModel.getcartcount}!!.collectAsState(initial =0)

    AnimatedSplashScreenTheme {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(bggrey),



        floatingActionButton = {
           MyfloatingActionButton(
               onClick = {
                 navController.navigate(Screens.Create.route)
               }
           )
        },
        topBar = {
MyTopbar(
    number = "${getcartcount}",
    title = "Products",
    needaction = true,
    onClick = {
      navController.navigate(Screens.Cart.route)
    }
)
        }

    ) {paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bggrey)
                .padding(paddingValues)
        ) {
            if (products.isEmpty()){
                TverrorText()

            }
            else{
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CategorylistRow(
                        newlist = updatedList,
                        onclick = {cat_id ->
                            category_id=cat_id?:0L

                        }

                    )
                    Spacer(modifier = Modifier.height(10.dp))




                    productComponent(
                        products =if (category_id==0L) products else productsbycat,
                        onclick = {product_id,price ->
                            scope.launch {
                                val detail=cartViewModel.checkproductexist(product_id)
                                if (detail==null){
                                    cartViewModel.insertcartproducts(
                                        subtotal = price*1,
                                        product_id = product_id
                                    )


                                }
                                else{
                                    Toast.makeText(context,"This product exist in  Cart",Toast.LENGTH_LONG).show()
                                }


                            }




                        }
                    )






                }
            }











        }
}



}





}