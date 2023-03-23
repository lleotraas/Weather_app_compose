package fr.lleotraas.myapplication.feature_weather.presentation.utils

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object WeatherScreen: Screen("weather_screen")
}
