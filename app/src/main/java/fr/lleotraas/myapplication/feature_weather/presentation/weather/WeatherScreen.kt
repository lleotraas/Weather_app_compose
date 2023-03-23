package fr.lleotraas.myapplication.feature_weather.presentation.weather

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import fr.lleotraas.myapplication.R
import fr.lleotraas.myapplication.feature_weather.presentation.main.components.DefaultTextView
import fr.lleotraas.myapplication.feature_weather.presentation.weather.component.WeatherItem
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = hiltViewModel()
) {

    var state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val initialValue = 0f
    val totalTime = 60L * 1000L
    val handleColor = Color.Green
    val inactiveBarColor = Color.DarkGray
    val activeBarColor = Color(0xFF34B900)
    val number = 0


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        LazyColumn(modifier = Modifier.fillMaxWidth()) {
//            items(state.weatherList) { weather ->
//                Log.e(javaClass.simpleName, "WeatherScreen: ville:${weather.name} température:${weather.main.temp}")
//                WeatherItem(modifier = Modifier.fillMaxSize(), weather = weather)
//            }
//        }
//        Spacer(modifier = Modifier.height(16.dp))
        val strokeWidth: Dp = 5.dp
        var size by remember {
            mutableStateOf(IntSize.Zero)
        }
        var value by remember {
            mutableStateOf(initialValue)
        }
        var currentTime by remember {
            mutableStateOf(totalTime)
        }
        var isTimeRunning by remember {
            mutableStateOf(false)
        }
        var index by remember {
            mutableStateOf(number)
        }
        LaunchedEffect(key1 = currentTime, key2 = isTimeRunning) {
            if (currentTime > 0 && isTimeRunning) {
                delay(100L)
                currentTime -= 100L
                value = currentTime / totalTime.toFloat()
                when(currentTime) {
                    10000L -> viewModel.getCurrentWeatherFromCity("Lyon")
                    8000L -> viewModel.getCurrentWeatherFromCity("Bordeaux")
                    6000L -> viewModel.getCurrentWeatherFromCity("Paris")
                    4000L -> viewModel.getCurrentWeatherFromCity("Marseille")
                    2000L -> viewModel.getCurrentWeatherFromCity("Brest")
                }
                if((currentTime.toInt()/100)%66 == 0) {
                    index++
                    if (index == 3) {
                        index = 0
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
                .onSizeChanged {
                    size = it
                }
        ) {
            Canvas(modifier = Modifier.size(200.dp)) {
                drawArc(
                    color = inactiveBarColor,
                    startAngle = -215f,
                    sweepAngle = 250f,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
                drawArc(
                    color = activeBarColor,
                    startAngle = -215f,
                    sweepAngle = 250f * value,
                    useCenter = false,
                    size = Size(size.width.toFloat(), size.height.toFloat()),
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
                val center = Offset(size.width /2f, size.height / 2f)
                val beta = (250f * value + 145f) * (PI / 180f).toFloat()
                val r = size.width / 2f
                val a = cos(beta) * r
                val b = sin(beta) * r
                drawPoints(
                    listOf(Offset(center.x + a, center.y + b)),
                    pointMode = PointMode.Points,
                    color = handleColor,
                    strokeWidth = (strokeWidth * 3f).toPx(),
                    cap = StrokeCap.Round
                )
            }
            Text(
                text = ((currentTime / 1000L)/ 60.0 * 100.0).toInt().toString(),
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Button(
                onClick = {
                    if (currentTime <= 0L) {
                        currentTime = totalTime
                        isTimeRunning = true
                    } else {
                        isTimeRunning = !isTimeRunning
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (!isTimeRunning || currentTime <= 0L) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                )
            ) {
                Text(
                    text = if (isTimeRunning && currentTime >= 0L) "Stop"
                    else if (!isTimeRunning && currentTime >= 0L) "Start"
                    else "Restart"
                )
            }
        }
        DefaultTextView(modifier = Modifier.fillMaxWidth(), text = navController.context.resources.getStringArray(R.array.sentence_array)[index])
    }

}