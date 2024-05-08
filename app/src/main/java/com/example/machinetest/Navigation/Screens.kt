package com.example.machinetest.Navigation

sealed class Screens(val route:String){
    object Home : Screens("home_screen")
    object Login:Screens("login")
    object Register:Screens("Register")
    object Cart:Screens("cart")
    object Create:Screens("create")
}