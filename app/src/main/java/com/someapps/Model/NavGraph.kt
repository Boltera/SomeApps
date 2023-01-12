package com.someapps.Model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.someapps.View.MainScreen
import com.someapps.View.SensorList
import com.someapps.View.infoScreen

@Composable
fun SetupNavGraph( navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(
            route = Screen.MainScreen.route
        ){
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.InfoScreen.route
        ){
            infoScreen(navController = navController)
        }
        composable(
            route = Screen.SensorList.route
        ){
            SensorList(navController = navController)
        }
    }
}