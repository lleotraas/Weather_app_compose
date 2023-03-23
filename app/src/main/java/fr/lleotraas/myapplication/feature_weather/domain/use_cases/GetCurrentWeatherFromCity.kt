package fr.lleotraas.myapplication.feature_weather.domain.use_cases

import fr.lleotraas.myapplication.feature_weather.domain.model.Weather
import fr.lleotraas.myapplication.feature_weather.domain.repository.WeatherRepository
import retrofit2.Response

class GetCurrentWeatherFromCity(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(city: String): Response<Weather> {
        return repository.getWeatherFromCity(city)
    }

}