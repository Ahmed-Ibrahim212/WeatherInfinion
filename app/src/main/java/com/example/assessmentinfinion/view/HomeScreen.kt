package com.example.assessmentinfinion.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assessmentinfinion.Routes
import com.example.assessmentinfinion.viewmodel.WeatherViewModel

@Composable
fun HomeScreen(vm: WeatherViewModel, navController: NavController,
               onShowDetail: (String) -> Unit) {

    val city by vm.city.collectAsState()
    val state by vm.weather.collectAsState()

    val context = LocalContext.current


    // Automatically navigate if data is successfully fetched
    LaunchedEffect(state.data) {
        state.data?.let {
            onShowDetail(it.city) // Navigate immediately
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // center horizontally

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = city,
            onValueChange = vm::onCityChange,
            label = { Text("Enter city") },
            modifier = Modifier.fillMaxWidth(0.7f)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                if (city.isNotBlank()) {
                    navController.navigate("detail/$city") {
                        popUpTo(Routes.Home) { inclusive = false }
                        launchSingleTop = true
                        restoreState = false
                    }
                } else {
                    vm.onCityChange(city) // triggers validation message
                }
            }) {
                Text("Get Weather")
            }

        }


        if (state.error != null) {
            Text(state.error!!, color = MaterialTheme.colorScheme.error)
        }

        if (state.loading) {
            CircularProgressIndicator()
        }
    }
}
