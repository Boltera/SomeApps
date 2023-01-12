package com.someapps.Model

sealed class Screen (val route: String) {
    object MainScreen: Screen(route = "main_screen")
    object InfoScreen: Screen(route = "info_screen")
    object SensorList: Screen(route = "sensorlist_screen")
}