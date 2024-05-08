package com.example.machinetest.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.machinetest.Domain.Model.LoginRequest
import com.example.machinetest.Presentation.Components.Submitbutton
import com.example.machinetest.Presentation.Components.passwordfieldcomponent
import com.example.machinetest.Presentation.Components.textfieldcomponent
import com.example.machinetest.Navigation.Screens
import com.example.machinetest.ViewModel.UserviewModel
import com.example.machinetest.Presentation.Components.txtbutton
import com.example.machinetest.ui.theme.emailicon
import com.example.machinetest.ui.theme.usernameicon
import com.example.machinetest.utility.ValidateLogin
import com.example.machinetest.utility.initialstate
import com.example.machinetest.utility.successstate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val viewModel= hiltViewModel<UserviewModel>()
    val context= LocalContext.current
    val scope= rememberCoroutineScope()
    var loginbtnloading by remember { mutableStateOf(false) }
    var loginsuccess by remember { mutableStateOf(initialstate) }

   if (loginsuccess.equals(successstate)){
       LaunchedEffect(key1 = context) {
           delay(550)
           loginbtnloading=false
           navController.navigate(Screens.Home.route)
       }

   }


    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        textfieldcomponent(
            value = username,
            changevalue ={
                username=it
            } ,
            labeltext = "UserName",
            placeholdertext = "UserName",
            keyboardType = KeyboardType.Email ,
            icon = painterResource(usernameicon),
            isError = false
        )
        Spacer(modifier = Modifier.height(15.dp))
        passwordfieldcomponent(
            value =password ,
            labeltext = "Password",
            changevalue = {
                 password=it
            },
            isError = false
        )
        Spacer(modifier = Modifier.height(20.dp))
        Submitbutton(
            onClick = {
                scope.launch {
                    if (!ValidateLogin(username,password)){
                        Toast.makeText(context,"please complete Login details",Toast.LENGTH_LONG).show()
                    }
                    else{
                     loginbtnloading=true
val request=LoginRequest(username,password)
    val getuser=viewModel.getuser(request.username?:"",request.password?:"")


             if (getuser!=null){

                 loginsuccess= successstate
                 Toast.makeText(context,"Login Success",Toast.LENGTH_LONG).show()

             }
             else{
                 Toast.makeText(context,"This user doesnot Exist",Toast.LENGTH_LONG).show()
                 delay(450)
                 loginbtnloading=false
                 loginsuccess= initialstate

             }



                    }





                }






            },
            loading = loginbtnloading,
            buttontxt ="Login"
        )
        Spacer(modifier = Modifier.height(25.dp))
        txtbutton(
            onClick = {
             navController.navigate(Screens.Register.route)
            },
            tvName = "you don't have an Account Click here to Register"
        )



    }



    }
}