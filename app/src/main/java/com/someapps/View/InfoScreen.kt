package com.someapps.View

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.someapps.Model.Screen

@Composable
fun infoScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SensorInformation()
        Text(
            text="Back to home",
            modifier = Modifier
                .clickable {
                    navController.navigate(Screen.MainScreen.route) {
                        popUpTo(Screen.MainScreen.route) {
                            inclusive = true
                        }
                    }
                }
        )
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
    //store sensor value
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
    val AllSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if(event.sensor.type == Sensor.TYPE_LIGHT) {
                lightSensorValue.value = event.values[0].toString()
            }
            if(event.sensor.type == Sensor.TYPE_PROXIMITY) {
                proximitySensorValue.value = event.values[0].toString()
            }
            if(event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                accelerometerSensorValue.value = listOf(event.values[0],event.values[1],event.values[2]).toString()
            }
            if(event.sensor.type == Sensor.TYPE_GRAVITY) {
                gravitySensorValue.value = listOf(event.values[0],event.values[1],event.values[2]).toString()
            }
        }
    }
    sensorManager.registerListener(
        AllSensorEventListener,
        lightSensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    sensorManager.registerListener(
        AllSensorEventListener,
        proximitySensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    sensorManager.registerListener(
        AllSensorEventListener,
        accelerometerSensor,
        10*1000000000
    )
    sensorManager.registerListener(
        AllSensorEventListener,
        gravitySensor,
        10*1000000000
    )
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