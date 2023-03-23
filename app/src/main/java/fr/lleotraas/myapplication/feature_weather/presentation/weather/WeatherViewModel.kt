package fr.lleotraas.myapplication.feature_weather.presentation.weather

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.lleotraas.myapplication.feature_weather.domain.use_cases.WeatherUseCases
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCases: WeatherUseCases
) : ViewModel() {

    private val _state = mutableStateOf(WeatherState())
    val state: State<WeatherState> = _state

    fun getCurrentWeatherFromCity(cityName: String) {
        viewModelScope.launch {
            val response = try {
                useCases.getCurrentWeatherFromCity(cityName)
            }catch (e:IOException) {
                Log.e(javaClass.simpleName, "getCurrentWeatherFromCity: you may have internet connection, ${e.message}")
                return@launch
            } catch (e: HttpException) {
                Log.e(javaClass.simpleName, "getCurrentWeatherFromCity: HttpException, unexpected response, ${e.message}")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val weather = response.body()!!
                state.value.weatherList.add(response.body()!!)
                Log.e(javaClass.simpleName, "getCurrentWeatherFromCity: city:${weather.name} state size:${state.value.weatherList.size}")
                _state.value = state.value.copy(
                    weatherList = state.value.weatherList
                )
            }
        }
    }

    private fun collectState() = flow {
        emit(state)
    }

}