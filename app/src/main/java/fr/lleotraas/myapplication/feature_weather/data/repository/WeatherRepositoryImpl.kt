package fr.lleotraas.myapplication.feature_weather.data.repository

import fr.lleotraas.myapplication.BuildConfig
import fr.lleotraas.myapplication.feature_weather.domain.model.Weather
import fr.lleotraas.myapplication.feature_weather.domain.repository.WeatherRepository
import fr.lleotraas.myapplication.feature_weather.domain.retrofit.WeatherApi
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherFromCity(city: String): Response<Weather> {
        return api.getCurrentWeatherFrom(
            city,
            "metric",
            BuildConfig.API_KEY
        )
    }
}