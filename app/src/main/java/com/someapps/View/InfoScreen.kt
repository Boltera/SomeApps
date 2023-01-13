package com.someapps.View

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.someapps.Model.Screen

@Composable
fun InfoScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SensorInformation()
        Card(
            shape = RoundedCornerShape(25),
            border = BorderStroke(width = 2.dp, Color.Black),
            modifier = Modifier
                .padding(10.dp,10.dp)
                .clickable {
                    navController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.MainScreen.route) {
                            inclusive = true
                        }
                    }
                }
        ) {
            Text(
                text="Back to home",
                modifier = Modifier
                    .padding(10.dp,10.dp)
            )
        }
    }
}

@Composable
fun SensorInformation() {
    val ctx = LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    //setup sensor
    val lightSensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    val proximitySensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    val accelerometerSensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val gravitySensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
    //create a mutable string to store sensor value
    val lightSensorValue = remember {
        mutableStateOf("")
    }
    val proximitySensorValue = remember {
        mutableStateOf("")
    }
    val accelerometerSensorValue = remember {
        mutableStateOf("")
    }
    val gravitySensorValue = remember {
        mutableStateOf("")
    }
    //Create sensor listener
    val allSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }
        override fun onSensorChanged(event: SensorEvent) {
            //if sensor event listener sensor type is light sensor
            if(event.sensor.type == Sensor.TYPE_LIGHT) {
                //store the event value to light sensor value
                lightSensorValue.value = event.values[0].toString()
            }
            //if sensor event listener sensor type is proximity sensor
            if(event.sensor.type == Sensor.TYPE_PROXIMITY) {
                //store the event value to proximity sensor value
                proximitySensorValue.value = event.values[0].toString()
            }
            //if sensor event listener sensor type is accelerometer sensor
            if(event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                //store the event value to accelerometer sensor value
                accelerometerSensorValue.value = listOf(event.values[0],event.values[1],event.values[2]).toString()
            }
            //if sensor event listener sensor type is gravity sensor
            if(event.sensor.type == Sensor.TYPE_GRAVITY) {
                //store the event value to gravity sensor value
                gravitySensorValue.value = listOf(event.values[0],event.values[1],event.values[2]).toString()
            }
        }
    }
    //register listener to keep the sensor value update
    //light sensor
    sensorManager.registerListener(
        allSensorEventListener,
        lightSensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    //proximity sensor
    sensorManager.registerListener(
        allSensorEventListener,
        proximitySensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    //accelerometer sensor
    sensorManager.registerListener(
        allSensorEventListener,
        accelerometerSensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    //gravity sensor
    sensorManager.registerListener(
        allSensorEventListener,
        gravitySensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    //Display all the sensor value that event listener get
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Light sensor:")
        Text(text = lightSensorValue.value)
        Text("Proximity sensor:")
        Text(text = proximitySensorValue.value)
        Text("Accelerometer sensor")
        Text(text = accelerometerSensorValue.value)
        Text("Gravity sensor")
        Text(text = gravitySensorValue.value)
    }
}