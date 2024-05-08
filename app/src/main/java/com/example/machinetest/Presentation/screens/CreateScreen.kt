package com.example.machinetest.screens

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.machinetest.Domain.Model.ProductRequest
import com.example.machinetest.Presentation.Components.CategorySpinner
import com.example.machinetest.Presentation.Components.Submitbutton
import com.example.machinetest.Presentation.Components.imagecontent
import com.example.machinetest.Presentation.Components.textfieldcomponent
import com.example.machinetest.Presentation.Components.txtbutton
import com.example.machinetest.ViewModel.ProductViewModel
import com.example.machinetest.ViewModel.UserviewModel
import com.example.machinetest.ui.theme.codeicon
import com.example.machinetest.ui.theme.descicon
import com.example.machinetest.ui.theme.priceicon
import com.example.machinetest.ui.theme.producticon
import com.example.machinetest.ui.theme.usernameicon
import com.example.machinetest.ui.theme.whitecolor
import com.example.machinetest.utility.getimagebyteArray
import com.example.machinetest.utility.validateproductdetails
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CreateScreen(navController: NavController){
var getimage by remember { mutableStateOf<Uri?>(null) }
var productName by remember { mutableStateOf("") }
var productdesc by remember { mutableStateOf("") }
var productprice by remember { mutableStateOf("") }
var productcode by remember { mutableStateOf("") }
var category_id by remember { mutableStateOf(0) }
var createloading by remember { mutableStateOf(false) }
val context= LocalContext.current
    val viewModel= hiltViewModel<ProductViewModel>()



    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri->
        if (uri != null) {
         getimage=uri
        }

    }


    if (createloading){
        LaunchedEffect(key1 = context) {
            delay(550)
            createloading=false
            navController.popBackStack()
        }
    }




    val permissionState = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)

    SideEffect {
        permissionState.launchPermissionRequest()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(whitecolor),
        contentAlignment = Alignment.Center
    ) {
     Column(
         modifier = Modifier
             .fillMaxWidth()
             .padding(16.dp),
         horizontalAlignment = Alignment.CenterHorizontally
     ) {

         imagecontent(uri = getimage)

         txtbutton(
             onClick = {
                 if (permissionState.status.isGranted){
                     galleryLauncher.launch("image/*")
                 }
                 else
                     permissionState.launchPermissionRequest()
             },
             tvName ="Click Here Choose Image"
         )
         Spacer(modifier = Modifier.height(10.dp))
         CategorySpinner(
             onClick = {cat_id ->
             category_id=cat_id
             }
         )
         Spacer(modifier = Modifier.height(10.dp))
         textfieldcomponent(
             value = productName,
             changevalue ={
                 productName=it
             } ,
             labeltext = "Product Name",
             placeholdertext = "Product Name",
             keyboardType = KeyboardType.Email ,
             icon = painterResource(producticon),
             isError = false
         )
         Spacer(modifier = Modifier.height(10.dp))
         textfieldcomponent(
             value = productdesc,
             changevalue ={
                 productdesc=it
             } ,
             labeltext = "Product Description",
             placeholdertext = "Product Description",
             keyboardType = KeyboardType.Email ,
             icon = painterResource(descicon),
             isError = false
         )
         Spacer(modifier = Modifier.height(10.dp))
         textfieldcomponent(
             value = productprice,
             changevalue ={
                 productprice=it
             } ,
             labeltext = "Product price",
             placeholdertext = "Product price",
             keyboardType = KeyboardType.Decimal ,
             icon = painterResource(priceicon),
             isError = false
         )
         Spacer(modifier = Modifier.height(10.dp))
         textfieldcomponent(
             value = productcode,
             changevalue ={
                 productcode=it
             } ,
             labeltext = "Product code",
             placeholdertext = "Product code",
             keyboardType = KeyboardType.Email ,
             icon = painterResource(codeicon),
             isError = false
         )
         Spacer(modifier = Modifier.height(17.dp))
         Submitbutton(
             onClick = {
             if (!validateproductdetails(productName,productdesc,productprice,productcode,category_id,getimage)){
               Toast.makeText(context,"please complete product details",Toast.LENGTH_LONG).show()

             }
             else{
                 createloading=true

                val request=ProductRequest(
                    productName,
                    productdesc,
                    productprice.toDouble(),
                    productcode,
                    category_id,
                    getimagebyteArray(context, getimage)
                )

                 viewModel.insertproducts(
                     pname = request.productName,
                     pdesc=request.productdesc,
                     pprice=request.productprice,
                     pcode=request.productcode,
                     cate_id = request.category_id?.toLong(),
                     image = request.product_image
                 )





             }




             },
             loading = createloading,
             buttontxt = "Upload"
         )



        }





    }




}