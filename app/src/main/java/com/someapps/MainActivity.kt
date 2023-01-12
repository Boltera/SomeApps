package com.someapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.someapps.Model.SetupNavGraph
import com.someapps.ui.theme.SomeAppsTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SomeAppsTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}