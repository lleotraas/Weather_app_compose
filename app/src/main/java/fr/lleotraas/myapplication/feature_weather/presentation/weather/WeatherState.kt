package fr.lleotraas.myapplication.feature_weather.presentation.weather

import fr.lleotraas.myapplication.feature_weather.domain.model.Weather

data class WeatherState(
    val weatherList: ArrayList<Weather> = ArrayList()
)
