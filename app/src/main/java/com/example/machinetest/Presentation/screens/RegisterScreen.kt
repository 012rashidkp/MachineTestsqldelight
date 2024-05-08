package com.example.machinetest.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ScaffoldSubcomposeInMeasureFix
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.machinetest.Domain.Model.RegisterRequest

import com.example.machinetest.ViewModel.UserviewModel
import com.example.machinetest.R
import com.example.machinetest.Presentation.Components.Submitbutton
import com.example.machinetest.Presentation.Components.passwordfieldcomponent
import com.example.machinetest.Presentation.Components.textfieldcomponent
import com.example.machinetest.ui.theme.emailicon
import com.example.machinetest.ui.theme.usernameicon
import com.example.machinetest.utility.ValidateRegister
import com.example.machinetest.utility.checkpasswordmatch
import com.example.machinetest.utility.validateemail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import kotlinx.coroutines.*
@Composable
fun RegisterScreen(navController: NavController){
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confpassword by remember { mutableStateOf("") }
    val viewModel= hiltViewModel<UserviewModel>()
    val context= LocalContext.current
    var registerloading by remember { mutableStateOf(false) }

if (registerloading){
    LaunchedEffect(key1 = context) {
      delay(650)
      registerloading=false
      navController.popBackStack()
    }


}


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center
    ){

       Column(
           modifier = Modifier
               .fillMaxWidth()
               .padding(16.dp)
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
           textfieldcomponent(
               value = email,
               changevalue ={
                   email=it
               } ,
               labeltext = "Email",
               placeholdertext = "Email",
               keyboardType = KeyboardType.Email ,
               icon = painterResource(emailicon),
               isError = false
           )
           Spacer(modifier = Modifier.height(15.dp))
           passwordfieldcomponent(
               value =password,
               labeltext = "Password",
               changevalue = {
                   password=it
               },
               isError = false
           )
           Spacer(modifier = Modifier.height(15.dp))
           passwordfieldcomponent(
               value =confpassword,
               labeltext = "Confirm Password",
               changevalue = {
                   confpassword=it
               },
               isError = false
           )
           Spacer(modifier = Modifier.height(20.dp))
           Submitbutton(
               enabled = true,
               onClick = {
              if (!ValidateRegister(username,email, password,confpassword)){
                  Toast.makeText(context,"please complete Registration details",Toast.LENGTH_LONG).show()
              }
              else if (!validateemail(email)){
                  Toast.makeText(context,"please enter a valid email",Toast.LENGTH_LONG).show()
              }

              else if (!checkpasswordmatch(password,confpassword)){
                  Toast.makeText(context,"Your Password are not match",Toast.LENGTH_LONG).show()
              }

              else{
            registerloading=true
            val result=RegisterRequest(username,email,password)
            viewModel.insertusers(result.username?:"",result.email?:"",result.password?:"")





              }




               },
               loading = registerloading,
               buttontxt ="Register"
           )





       }




    }
}