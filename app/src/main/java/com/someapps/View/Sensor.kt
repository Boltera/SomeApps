package com.someapps.View

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.someapps.Model.Screen

@Composable
fun SensorList(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        displaySensors()
        Text(
            text = "Back to home",
            modifier = Modifier.clickable {
                navController.navigate(Screen.MainScreen.route) {
                    popUpTo(route = Screen.MainScreen.route) {
                        inclusive = true
                    }
                }
            }
        )
    }

}

@Composable
fun displaySensors() {
    // on below line we are creating
    // a variable for a context
    val ctx = LocalContext.current

    // on below line we are creating a column
    Column(
        // on below line we are specifying modifier
        // and setting max height and max width
        // for our column
        modifier = Modifier
            .fillMaxWidth()
            // on below line we are
            // adding padding for our column
            .padding(5.dp),
        // on below line we are specifying horizontal
        // and vertical alignment for our column
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // on below line we are initializing our sensor manager.
        var sensorManager: SensorManager =
            ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // on below line we are creating list for device sensors.
        var deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)


        // on below line we are creating a simple text
        // in which we are displaying a text as Object is
        Text(
            text = "Sensors in Devices are : ",
            // on below line we are setting text color
            color = Color.Black,

            // on below line we are specifying font weight
            fontWeight = FontWeight.Bold,

            // on below line we are specifying font family.
            fontFamily = FontFamily.Default,

            // on below line we are specifying
            // font size and padding from all sides.
            fontSize = 20.sp, modifier = Modifier.padding(5.dp)
        )

        // on below line creating a variable for sensor data.
        var sensorsData = ""

        // on below line adding all sensors from
        // device sensors in our variable.
        for (sens in deviceSensors) {
            sensorsData = sensorsData + sens.name + "  \n\n"
        }

        // on below line we are creating a simple text
        // in which we are displaying a text as all
        // sensors in device
        Text(
            text = sensorsData,
            // on below line we are setting text color
            color = Color.Black,

            // on below line we are specifying font weight
            fontWeight = FontWeight.Black,

            // on below line we are specifying font family.
            fontFamily = FontFamily.Default,

            // on below line adding text alignment.
            textAlign = TextAlign.Center,

            // on below line we are specifying
            // font size and padding from all sides.
            fontSize = 12.sp,
            modifier = Modifier.padding(5.dp)
        )

    }

}