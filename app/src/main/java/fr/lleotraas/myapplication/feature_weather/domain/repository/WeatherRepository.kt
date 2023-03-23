package fr.lleotraas.myapplication.feature_weather.domain.repository

import fr.lleotraas.myapplication.feature_weather.domain.model.Weather
import retrofit2.Response

interface WeatherRepository {

   suspend fun getWeatherFromCity(city: String): Response<Weather>

//    fun getWeatherList(): Flow<ArrayList<Weather>>
//
//    fun clearWeatherList()

}