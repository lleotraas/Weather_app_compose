package fr.lleotraas.myapplication.feature_weather.presentation.utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.lleotraas.myapplication.feature_weather.presentation.main.components.DefaultButton
import fr.lleotraas.myapplication.feature_weather.presentation.main.components.DefaultTextView

@Composable
fun MainScreen(
    navController: NavController
) {
//    val scaffoldState = rememberScaffoldState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DefaultTextView(
            modifier = Modifier.fillMaxWidth(),
            text = "Bienvenue dans l'application"
        )
        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Voir la météo",
            onClick = {
                navController.navigate(Screen.WeatherScreen.route)
            }
        )
    }
}