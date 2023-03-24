package fr.lleotraas.myapplication.feature_weather.presentation.weather.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.lleotraas.myapplication.feature_weather.domain.model.Weather

@Composable
fun WeatherItem(
    weather: Weather,
    icon: Bitmap
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                shape = RoundedCornerShape(5.dp),
                width = 2.dp,
                color = MaterialTheme.colors.primary
            )
    ) {
        Text(
            text = weather.name,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            color = Color.White,
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = weather.main.temp.toString().replaceAfter(".","").replace(".","") + "°",
                modifier = Modifier.padding(16.dp),
                color = Color.White,
                style = MaterialTheme.typography.body1
            )
            Image(
                bitmap = icon.asImageBitmap(),
                contentDescription = "current weather icon",
                modifier = Modifier.size(72.dp)
            )
        }
    }
}