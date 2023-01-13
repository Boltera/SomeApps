package com.someapps.View

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.someapps.Model.Screen


@Composable
fun MainScreen(navController: NavHostController) {
    //Use column display content
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            //give rounded shape
            shape = RoundedCornerShape(25),
            //add border
            border = BorderStroke(width = 2.dp,Color.Black),
            //spacing
            modifier = Modifier
                .padding(5.dp,5.dp)
                .clickable {
                    navController.navigate(route = Screen.InfoScreen.route)
                }
        ) {
            Text(text="Active sensor information",
                modifier = Modifier
                    .padding(10.dp,10.dp)
            )
        }
        Card(
            shape = RoundedCornerShape(25),
            border = BorderStroke(width = 2.dp,Color.Black),
            modifier = Modifier
                .padding(5.dp,5.dp)
                .clickable {
                    navController.navigate(route = Screen.SensorList.route)
                }
        ) {
            Text(
                text = "Sensor list",
                modifier = Modifier
                    .padding(10.dp,10.dp)

            )
        }
    }
}

