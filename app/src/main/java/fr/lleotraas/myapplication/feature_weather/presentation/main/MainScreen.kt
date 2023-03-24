package fr.lleotraas.myapplication.feature_weather.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.lleotraas.myapplication.feature_weather.presentation.main.components.DefaultButton
import fr.lleotraas.myapplication.feature_weather.presentation.main.components.DefaultTextView
import fr.lleotraas.myapplication.feature_weather.presentation.utils.Screen
import fr.lleotraas.myapplication.R

@Composable
fun MainScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DefaultTextView(
            modifier = Modifier.fillMaxWidth(),
            text = navController.context.resources.getString(R.string.greetings)
        )
        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            text = navController.context.resources.getString(R.string.see_meteo),
            onClick = {
                navController.navigate(Screen.WeatherScreen.route)
            }
        )
    }
}