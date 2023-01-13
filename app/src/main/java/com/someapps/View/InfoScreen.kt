package com.someapps.View

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
                .padding(10.dp, 10.dp)
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
    val magneticSensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
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
    val magneticSensorValue = remember {
        mutableStateOf("")
    }
    var isSensorEnable = false

    //Create sensor listener
    val allSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }
        override fun onSensorChanged(event: SensorEvent) {
            //if sensor event listener sensor type is light sensor
            if(event.sensor.type == Sensor.TYPE_LIGHT) {
                //create event value string
                val value = event.values[0].toString()
                //store the event value to light sensor value
                lightSensorValue.value = value
            }
            //if sensor event listener sensor type is proximity sensor
            if(event.sensor.type == Sensor.TYPE_PROXIMITY) {
                //create event value string
                val value = event.values[0].toString()
                //store the event value to proximity sensor value
                proximitySensorValue.value = value
            }
            //if sensor event listener sensor type is accelerometer sensor
            if(event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                //create event value string
                val value = "x: "+ event.values[0].toString() +" y: "+ event.values[1].toString() +" z: "+ event.values[2].toString()
                //store the event value to accelerometer sensor value
                accelerometerSensorValue.value = value
            }
            //if sensor event listener sensor type is gravity sensor
            if(event.sensor.type == Sensor.TYPE_GRAVITY) {
                //create event value string
                val value = "x: "+ event.values[0].toString() +" y: "+ event.values[1].toString() +" z: "+ event.values[2].toString()
                //store the event value to gravity sensor value
                gravitySensorValue.value = value
            }
            //if sensor event listener sensor type is magnetic sensor
            if(event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                //create event value string
                val value = "x: "+ event.values[0].toString() +" y: "+ event.values[1].toString() +" z: "+ event.values[2].toString()
                //store the event value to magnetic sensor value
                magneticSensorValue.value = value
            }
        }
    }
    fun registerListen() {
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
            //magnetic sensor
            sensorManager.registerListener(
                allSensorEventListener,
                magneticSensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
    }
    //unregister listener to avoid reach listener 128 limit
    fun unregisterListen(){
            sensorManager.unregisterListener(
                allSensorEventListener,
                lightSensor
            )
            sensorManager.unregisterListener(
                allSensorEventListener,
                proximitySensor
            )
            sensorManager.unregisterListener(
                allSensorEventListener,
                accelerometerSensor
            )
            sensorManager.unregisterListener(
                allSensorEventListener,
                gravitySensor
            )
            sensorManager.unregisterListener(
                allSensorEventListener,
                magneticSensor
            )
    }
    //switch sensor listener state
    fun switch() {
        if(isSensorEnable){
            isSensorEnable = false
            registerListen()
        }else{
            isSensorEnable = true
            unregisterListen()
        }
    }
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
        Text("Magnetic sensor")
        Text(text = magneticSensorValue.value)
        Row(
            modifier = Modifier
                .padding(10.dp,10.dp)
        ){
            Card(
                border = BorderStroke(width = 2.dp, Color.Black),
                shape = RoundedCornerShape(25),
                modifier = Modifier
                    .padding(10.dp,10.dp)
                    .clickable { switch() }) {
                Text(
                    modifier = Modifier.padding(10.dp,10.dp),
                    text = "Start sensor")
            }
        }
    }
}
