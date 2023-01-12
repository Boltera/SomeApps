package com.someapps.View

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.verticalScroll
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
    val lazyListState = rememberLazyListState()
    val scrolledY = 0f
    val previousOffset = 0
    LazyColumn(modifier = Modifier
        .fillMaxWidth(),
        lazyListState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Modifier.fillMaxWidth()
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
    }
}

@Composable
fun displaySensors() {
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var sensorManager: SensorManager =
            ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        var deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        Text(
            text = "Sensors in Devices are : ",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp, modifier = Modifier.padding(5.dp)
        )

        var sensorsData = ""
        for (sens in deviceSensors) {
            sensorsData = sensorsData + sens.name + "  \n\n"
        }
        Text(
            text = sensorsData,
            color = Color.Black,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.Default,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.padding(5.dp)
        )

    }

}