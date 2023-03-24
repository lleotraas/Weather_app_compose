package fr.lleotraas.myapplication.feature_weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.lleotraas.myapplication.feature_weather.presentation.main.MainScreen
import fr.lleotraas.myapplication.feature_weather.presentation.utils.Screen
import fr.lleotraas.myapplication.feature_weather.presentation.weather.WeatherScreen
import fr.lleotraas.myapplication.ui.theme.WeatherAppComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppComposeTheme {
                Surface(color = Color(0xFF101010)) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {
                        composable(route = Screen.MainScreen.route) {
                            MainScreen(navController = navController)
                        }
                        composable(route = Screen.WeatherScreen.route) {
                            WeatherScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}