package fr.lleotraas.myapplication.feature_weather.presentation.weather

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.lleotraas.myapplication.R
import fr.lleotraas.myapplication.feature_weather.presentation.main.components.DefaultTextView
import fr.lleotraas.myapplication.feature_weather.presentation.utils.SequenceType
import fr.lleotraas.myapplication.feature_weather.presentation.utils.Utils
import fr.lleotraas.myapplication.feature_weather.presentation.weather.component.WeatherItem
import kotlinx.coroutines.delay

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val initialValue = 0f
    val totalTime = 60L * 1000L
    val inactiveBarColor = Color.DarkGray
    val activeBarColor = Color(0xFF34B900)
    val number = 0

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var value by remember {
            mutableStateOf(initialValue)
        }
        var currentTime by rememberSaveable {
            mutableStateOf(0L)
        }
        var isTimeRunning by rememberSaveable {
            mutableStateOf(true)
        }
        var index by rememberSaveable {
            mutableStateOf(number)
        }
        if (!isTimeRunning) {
            Column(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.weatherList) { weather ->
                        WeatherItem(
                            weather = weather,
                            state.weatherIconList[weather.id]!!
                        )
                    }
                }
                Button(
                    onClick = {
                    currentTime = 0L
                    isTimeRunning = true
                    viewModel.state.value.weatherIconList = HashMap()
                    viewModel.state.value.weatherList = ArrayList()
                },
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = navController.context.resources.getString(R.string.again_btn))
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center,

        ) {
            if (isTimeRunning) {

                LaunchedEffect(key1 = currentTime, key2 = isTimeRunning) {
                    if (currentTime < totalTime && isTimeRunning) {
                        delay(1000L)
                        currentTime += 1000L
                        value = ((currentTime / 1000L)/ 60.0 * 100.0).toFloat()
                        viewModel.getCurrentWeatherFromCity(Utils.getCityName(currentTime))
                        if(state.sequence == SequenceType.WeatherIconSequence) {
                            viewModel.getBitmapFrom(state.weatherList.last().weather[0].icon, state.weatherList.last().id)
                        }
                        index = Utils.incrementIndex(index, currentTime)
                        if(currentTime == totalTime) {
                            isTimeRunning = false
                        }
                    }
                }
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "${((currentTime / 1000L) / 60.0 * 100.0).toInt()}%",
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center

                    )
                    LinearProgressIndicator(
                        progress = value / 100,
                        color = activeBarColor,
                        backgroundColor = inactiveBarColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                            .height(8.dp)
                            .clip(CircleShape)
                    )
                    DefaultTextView(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = navController.context.resources.getStringArray(R.array.sentence_array)[index]
                    )
                }
            }
        }
    }
}