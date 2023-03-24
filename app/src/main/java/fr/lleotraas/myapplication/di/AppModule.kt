package fr.lleotraas.myapplication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.lleotraas.myapplication.feature_weather.data.repository.WeatherRepositoryImpl
import fr.lleotraas.myapplication.feature_weather.domain.repository.WeatherRepository
import fr.lleotraas.myapplication.feature_weather.domain.retrofit.IconApi
import fr.lleotraas.myapplication.feature_weather.domain.retrofit.WeatherApi
import fr.lleotraas.myapplication.feature_weather.domain.use_cases.GetCurrentWeatherFromCity
import fr.lleotraas.myapplication.feature_weather.domain.use_cases.GetWeatherIcon
import fr.lleotraas.myapplication.feature_weather.domain.use_cases.WeatherUseCases
import fr.lleotraas.myapplication.feature_weather.presentation.utils.Utils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL_WEATHER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherIconApi(): IconApi {
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL_WEATHER_ICON)
            .build()
            .create(IconApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi, iconApi: IconApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi, iconApi)
    }

    @Provides
    @Singleton
    fun provideWeatherUseCases(repository: WeatherRepository): WeatherUseCases {
        return WeatherUseCases(
            getCurrentWeatherFromCity = GetCurrentWeatherFromCity(repository),
            getWeatherIcon = GetWeatherIcon(repository)
        )
    }

}