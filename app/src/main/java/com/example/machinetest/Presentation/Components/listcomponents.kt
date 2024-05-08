package com.example.machinetest.Presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.machinetest.Domain.Model.Categories
import com.example.machinetest.R
import com.example.machinetest.ui.theme.black
import com.example.machinetest.ui.theme.deleteicon
import com.example.machinetest.ui.theme.greylight
import com.example.machinetest.ui.theme.popins_medium
import com.example.machinetest.ui.theme.popins_regular
import com.example.machinetest.ui.theme.tealcolor
import com.example.machinetest.ui.theme.whitecolor
import productdb.GetAllCartinfo
import productdb.ProductEntity

@Composable
fun CategorylistRow(
    newlist:List<Categories>,
    onclick:(cat_id:Long?)->Unit
){
    var selectedposition by remember { mutableStateOf(0) }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = PaddingValues(
            start = 5.dp,
            top = 5.dp,
            end = 5.dp,
            bottom = 5.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(newlist.size){index->

            val datas=newlist[index]
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(if (selectedposition == index) tealcolor else greylight)
                    .clickable {
                        selectedposition = index
                        onclick(datas.id.toLong())
                    },

                ) {
                  Text(
                      text =datas.catName?:"",
                      fontSize = 16.sp,
                      color = if (selectedposition==index) whitecolor else black,
                      fontFamily = popins_regular,
                      textAlign = TextAlign.Center,
                      modifier = Modifier
                          .padding(10.dp)
                  )


                  }



        }


    }


}
@Composable
fun productComponent(
    products:List<ProductEntity>,
    onclick:(product_id:Long,price:Double)->Unit
){
  LazyColumn(
      modifier = Modifier
          .fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally
  ) {
   items(products.size){index ->
       val datas=products[index]
       Card(
           colors = CardDefaults.cardColors(whitecolor),
           modifier = Modifier
               .fillMaxWidth()
               .clip(RoundedCornerShape(16.dp))
               .clickable {
               }
               .padding(horizontal = 10.dp)
               .wrapContentHeight(),
           elevation = CardDefaults.cardElevation(8.dp),
           shape = RoundedCornerShape(16.dp)
       ) {
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(all = 12.dp),
               verticalAlignment = Alignment.CenterVertically,

           ) {
               AsyncImage(
                   model = datas.prod_image,
                   contentDescription = "",
                   modifier = Modifier
                       .width(65.dp)
                       .height(65.dp)
                       .clip(RoundedCornerShape(16.dp)),
                   contentScale = ContentScale.Crop,
               )

               Column(
                   modifier = Modifier
                       .wrapContentWidth()
                       .padding(start = 5.dp)

               ) {
                  Row {
                      Text(
                          text = datas.productName,
                          color = black,
                          fontSize = 19.sp,
                          fontFamily = popins_medium
                      )
                      Spacer(modifier = Modifier.width(10.dp))
                      Text(
                          text ="Rs ${datas.prod_price}",
                          color = tealcolor,
                          fontSize = 14.sp,
                          fontFamily = popins_medium
                      )
                      
                  }
                   Text(
                       text =datas.prodDesc,
                       color = Color.Gray,
                       fontSize = 14.sp,
                       fontFamily = popins_regular
                   )
                   Spacer(modifier = Modifier.height(6.dp))

                   Button(
                       onClick = {
                           onclick(datas.productid,datas.prod_price)
                       },

                       modifier = Modifier
                           .wrapContentWidth()
                           .height(35.dp)
                           .padding(horizontal = 10.dp),

                       shape = RoundedCornerShape(12.dp),

                       colors = ButtonDefaults.buttonColors(containerColor = tealcolor),
                   ) {
                       Text(
                           text = "Add to Cart",
                           color = whitecolor,
                           fontSize = 14.sp
                       )

                   }


               }
              // add to cart button




           }



       }









   }


  }




}

@Composable
fun cartcomponent(
    items:List<GetAllCartinfo>,
    qtyclick:(cart_id:Long?, qty:Long?,subtotal:Double?,product_id:Long?)->Unit,
    deleteclick:(cart_id:Long)->Unit


){
var currentqty by remember { mutableStateOf(1L) }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items.size){index ->
            val datas=items[index]

            Card(
                colors = CardDefaults.cardColors(whitecolor),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                    }
                    .padding(horizontal = 10.dp)
                    .wrapContentHeight(),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    AsyncImage(
                        model = datas.prod_image,
                        contentDescription = "",
                        modifier = Modifier
                            .width(65.dp)
                            .height(65.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop,
                    )

                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(start = 5.dp)

                    ) {
                        Row {
                            Text(
                                text = datas.productName,
                                color = black,
                                fontSize = 19.sp,
                                fontFamily = popins_medium
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text ="Rs ${datas.sub_total}",
                                color = tealcolor,
                                fontSize = 14.sp,
                                fontFamily = popins_medium
                            )

                        }
                       Row(
                           modifier = Modifier
                               .fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceBetween
                       ) {
                           Text(
                               text =datas.prodDesc,
                               color = Color.Gray,
                               fontSize = 14.sp,
                               fontFamily = popins_regular
                           )
                           AsyncImage(
                               model = deleteicon,
                               contentDescription = "",
                               modifier = Modifier
                                   .height(26.dp)
                                   .width(26.dp)
                                   .clickable {
                                       deleteclick(datas.cartid)
                                   }
                           )





                       }



                        Spacer(modifier = Modifier.height(6.dp))

                     Row {
                         Button(
                             onClick = {
                                 if (currentqty>1){
                                        --currentqty


                                     qtyclick(datas.cartid, currentqty,datas.prod_price,datas.product_id)
                                 }



                             },

                             modifier = Modifier
                                 .wrapContentWidth()
                                 .height(35.dp)
                                 .padding(horizontal = 10.dp),

                             shape = RoundedCornerShape(12.dp),

                             colors = ButtonDefaults.buttonColors(containerColor = tealcolor),
                         ) {
                             Text(
                                 text = "-",
                                 color = whitecolor,
                                 fontSize = 14.sp
                             )

                         }

                         Text(
                             text = "${datas.qty}",
                             color = black,
                             fontSize = 16.sp,
                             modifier = Modifier
                                 .padding(top = 5.dp)
                         )

                         Button(
                             onClick = {
                              ++currentqty

                                 qtyclick(datas.cartid, currentqty,datas.prod_price,datas.product_id)

                             },

                             modifier = Modifier
                                 .wrapContentWidth()
                                 .height(35.dp)
                                 .padding(horizontal = 10.dp),

                             shape = RoundedCornerShape(12.dp),

                             colors = ButtonDefaults.buttonColors(containerColor = tealcolor),
                         ) {
                             Text(
                                 text = "+",
                                 color = whitecolor,
                                 fontSize = 14.sp
                             )

                         }




                     }





                    }
                    // add to cart button




                }



            }







        }




    }





}