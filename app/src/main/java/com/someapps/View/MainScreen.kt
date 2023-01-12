package com.someapps.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.someapps.Model.Screen


@Composable
fun MainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="Active sensor information",
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.InfoScreen.route)
            })
        Text(
            text = "Sensor list",
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.SensorList.route)
            }
        )
    }
}

