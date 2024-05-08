package com.example.machinetest.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.machinetest.Animation.EnterExitAnimation
import com.example.machinetest.screens.CartScreen
import com.example.machinetest.screens.CreateScreen
import com.example.machinetest.screens.HomeScreen
import com.example.machinetest.screens.LoginScreen
import com.example.machinetest.screens.RegisterScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ) {

        composable(route = Screens.Register.route) {
            EnterExitAnimation { RegisterScreen(navController = navController) }

        }
        composable(route=Screens.Login.route){
            EnterExitAnimation { LoginScreen(navController = navController) }

        }
        composable(route=Screens.Home.route){
           EnterExitAnimation { HomeScreen(navController = navController) }

        }

        composable(route=Screens.Cart.route){
            EnterExitAnimation { CartScreen(navController = navController) }

        }

        composable(route=Screens.Create.route){
            EnterExitAnimation { CreateScreen(navController = navController) }

        }



    }

}
