package com.someapps.Model

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.someapps.View.InfoScreen
import com.someapps.View.MainScreen
import com.someapps.View.SensorList

@Composable
//Create a NavGraph for screen navigate
fun SetupNavGraph( navController: NavHostController) {
    //startDestination is the init screen
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        //read MainScreen.kt function MainScreen be the screen
        composable(
            route = Screen.MainScreen.route
        ){
            MainScreen(navController = navController)
        }
        //read InfoScreen.kt function InfoScreen be the screen
        composable(
            route = Screen.InfoScreen.route
        ){
            InfoScreen(navController = navController)
        }
        //read Sensor.kt function SensorList be the screen
        composable(
            route = Screen.SensorList.route
        ){
            SensorList(navController = navController)
        }
    }
}