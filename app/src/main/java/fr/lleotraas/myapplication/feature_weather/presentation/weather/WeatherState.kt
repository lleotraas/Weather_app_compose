package fr.lleotraas.myapplication.feature_weather.presentation.weather

import android.graphics.Bitmap
import fr.lleotraas.myapplication.feature_weather.domain.model.Weather
import fr.lleotraas.myapplication.feature_weather.presentation.utils.SequenceType

data class WeatherState(
    var weatherList: ArrayList<Weather> = ArrayList(),
    var weatherIconList: HashMap<Int, Bitmap> = HashMap(),
    var sequence: SequenceType = SequenceType.WeatherSequence
)
