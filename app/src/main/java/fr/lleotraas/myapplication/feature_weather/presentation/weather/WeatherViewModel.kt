package fr.lleotraas.myapplication.feature_weather.presentation.weather

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.lleotraas.myapplication.feature_weather.domain.use_cases.WeatherUseCases
import fr.lleotraas.myapplication.feature_weather.presentation.utils.SequenceType
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
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
                _state.value = state.value.copy(
                    weatherList = state.value.weatherList,
                    sequence = SequenceType.WeatherIconSequence
                )
            }
        }
    }

    fun getBitmapFrom(iconId: String, weatherId: Int) {
        viewModelScope.launch {
            useCases.getWeatherIcon(iconId).enqueue(object : retrofit2.Callback<ResponseBody> {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.body() == null || !response.isSuccessful || response.errorBody() != null) {
                        return
                    }
                    val bytes = response.body()!!.bytes()
                    state.value.weatherIconList[weatherId] = (BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
                    _state.value = state.value.copy(
                        weatherIconList = state.value.weatherIconList
                    )
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    return
                }
            })
        }
        _state.value = state.value.copy(
            sequence = SequenceType.WeatherSequence
        )
    }
}