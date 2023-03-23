package fr.lleotraas.myapplication.feature_weather.domain.model

data class WeatherX(
    val description: String,
    var icon: String,
    val id: Int,
    val main: String
)
