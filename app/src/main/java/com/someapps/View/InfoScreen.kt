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
        proximityValue()
        accelerometerValue()
        GravityValue()
        LightValue()
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
fun LightValue(){
    val ctx= LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val lightSensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    val sensorV = remember {
        mutableStateOf("")
    }
    val lightSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if(event.sensor.type == Sensor.TYPE_LIGHT) {
                sensorV.value = event.values[0].toString()
            }
        }
    }
    sensorManager.registerListener(
        lightSensorEventListener,
        lightSensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Light sensor value")
        Text(text = sensorV.value)
    }
}

@Composable
fun GravityValue(){
    val ctx= LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val gravitySensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
    var sensorVlist: List<Float>
    val sensorV = remember {
        mutableStateOf("")
    }
    val gravitySensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if(event.sensor.type == Sensor.TYPE_GRAVITY) {
                sensorVlist = listOf(event.values[0],event.values[1],event.values[2])
                sensorV.value = sensorVlist.toString()
            }
        }
    }
    sensorManager.registerListener(
        gravitySensorEventListener,
        gravitySensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Gravity sensor value:")
        Text(text = sensorV.value)
    }
}

//show accelerometer value
@Composable
fun accelerometerValue(){
    val ctx= LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val accelerometerSensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    var sensorVlist: List<Float>
    val sensorV = remember {
        mutableStateOf("")
    }
    val accelerometerSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if(event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                sensorVlist = listOf(event.values[0],event.values[1],event.values[2])
                sensorV.value = sensorVlist.toString()
            }
        }
    }
    sensorManager.registerListener(
        accelerometerSensorEventListener,
        accelerometerSensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "AccelerometerValue:")
        Text(text = sensorV.value)
    }
}

//Show proximity sensor detection
@Composable
fun proximityValue(){
    val ctx = LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val proximitySensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    val sensorStatus = remember {
        mutableStateOf("")
    }
    val proximitySensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] < 5f) {
                    sensorStatus.value = "Near"
                } else {
                    sensorStatus.value = "Away"
                }
            }
        }
    }
    sensorManager.registerListener(
        proximitySensorEventListener,
        proximitySensor,
        SensorManager.SENSOR_DELAY_NORMAL
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Proximity Sensor detected object:")
        Text(text = sensorStatus.value)
    }
}