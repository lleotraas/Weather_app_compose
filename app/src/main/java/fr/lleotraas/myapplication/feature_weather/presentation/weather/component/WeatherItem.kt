package fr.lleotraas.myapplication.feature_weather.presentation.weather.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.lleotraas.myapplication.feature_weather.domain.model.Weather

@Composable
fun WeatherItem(
    modifier: Modifier,
    weather: Weather
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = weather.name,
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = weather.main.temp.toString(),
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontSize = 24.sp
        )
    }
    Spacer(modifier = Modifier.width(16.dp))
}