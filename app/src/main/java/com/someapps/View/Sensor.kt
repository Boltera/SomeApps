package com.someapps.View

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.someapps.Model.Screen

@Composable
fun SensorList(navController: NavController){
    //use rememberLazyListState function to store the scroll state
    val lazyListState = rememberLazyListState()
    //LazyColumn for scroll action
    LazyColumn(modifier = Modifier
        .fillMaxWidth(),
        lazyListState,
        //center content vertical
        verticalArrangement = Arrangement.Center,
        //align content horizontal
        horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            // fill screen width for auto fit screen
            Modifier.fillMaxWidth()
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                //run display sensors function
                DisplaySensors()
                Card(
                    shape = RoundedCornerShape(25),
                    border = BorderStroke(width = 2.dp, Color.Black),
                    modifier = Modifier
                        .padding(10.dp,10.dp)
                        .clickable {
                            // navigate screen to main screen
                            navController.navigate(Screen.MainScreen.route) {
                                // avoid it stack more screen when back to main screen
                                popUpTo(route = Screen.MainScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                ) {
                    Text(
                        text = "Back to home",
                        // add clickable property to text
                        modifier = Modifier
                            .padding(10.dp,10.dp)

                    )
                }
            }
        }
    }
}

// show device sensors
@Composable
fun DisplaySensors() {
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // init sensorManager
        val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // create a list that store sensor information from sensorManager
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        Text(
            text = "Sensors in this device: ",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(5.dp)
        )
        // create a string of sensor information for display in text
        var sensorsData = ""
        for (sensor in deviceSensors) {
            sensorsData = sensorsData + sensor.name + "  \n\n"
        }
        Text(
            text = sensorsData,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )
    }
}