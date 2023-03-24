package fr.lleotraas.myapplication.feature_weather.domain.use_cases

import fr.lleotraas.myapplication.feature_weather.domain.repository.WeatherRepository
import okhttp3.ResponseBody
import retrofit2.Call

class GetWeatherIcon(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(iconId: String): Call<ResponseBody> {
        return repository.getWeatherIcon(iconId)
    }

}