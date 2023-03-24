package fr.lleotraas.myapplication.feature_weather.domain.repository

import fr.lleotraas.myapplication.feature_weather.domain.model.Weather
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

interface WeatherRepository {

   suspend fun getWeatherFromCity(city: String): Response<Weather>

   suspend fun getWeatherIcon(iconId: String): Call<ResponseBody>

}