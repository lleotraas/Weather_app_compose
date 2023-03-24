package fr.lleotraas.myapplication.feature_weather.data.repository

import fr.lleotraas.myapplication.BuildConfig
import fr.lleotraas.myapplication.feature_weather.domain.model.Weather
import fr.lleotraas.myapplication.feature_weather.domain.repository.WeatherRepository
import fr.lleotraas.myapplication.feature_weather.domain.retrofit.IconApi
import fr.lleotraas.myapplication.feature_weather.domain.retrofit.WeatherApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
    private val iconApi: IconApi
) : WeatherRepository {

    override suspend fun getWeatherFromCity(city: String): Response<Weather> {
        return api.getCurrentWeatherFrom(
            city,
            "metric",
            BuildConfig.API_KEY
        )
    }

    override suspend fun getWeatherIcon(iconId: String): Call<ResponseBody> {
        return iconApi.getWeatherIcon(iconId)
    }
}