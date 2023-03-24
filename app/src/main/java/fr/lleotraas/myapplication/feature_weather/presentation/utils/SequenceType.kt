package fr.lleotraas.myapplication.feature_weather.presentation.utils

sealed class SequenceType {
    object WeatherSequence: SequenceType()
    object WeatherIconSequence: SequenceType()
}
