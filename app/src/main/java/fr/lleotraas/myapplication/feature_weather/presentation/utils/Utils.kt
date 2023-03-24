package fr.lleotraas.myapplication.feature_weather.presentation.utils

object Utils {

    const val BASE_URL_WEATHER = "https://api.openweathermap.org/"
    const val BASE_URL_WEATHER_ICON = "https://openweathermap.org/"

    fun getCityName(currentTime: Long): String {
        return when(currentTime) {
            10000L -> "rennes,fr"
            20000L -> "paris,fr"
            30000L -> "nantes,fr"
            40000L -> "bordeaux,fr"
            50000L -> "lyon,fr"
            else -> ""
        }
    }

    fun incrementIndex(index: Int, currentTime: Long): Int {
        var indexToReturn = index
        if((currentTime.toInt()/1000)%6 == 0) {
            indexToReturn++
            if (indexToReturn == 3) {
                indexToReturn = 0
            }
        }
        return indexToReturn
    }

}