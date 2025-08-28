package com.example.assessmentinfinion.view

import android.widget.Toast
import androidx.compose.foundation.background
import com.example.assessmentinfinion.viewmodel.DetailViewModel


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assessmentinfinion.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    city: String,
    vm: DetailViewModel,
    navController: NavController
) {
    val state by vm.state.collectAsState()

    // Load weather data on screen start
    LaunchedEffect(city) {
        if (city.isNotBlank()) vm.load(city)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Weather: $city") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack(
                            route = Routes.Home, // pop back to Home explicitly
                            inclusive = false
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        // Full-screen Box to occupy remaining space
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.loading -> {
                    CircularProgressIndicator()
                }
                state.error != null -> {
                    Text(
                        text = "Error: ${state.error}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                state.data != null -> {
                    val info = state.data
                    // Example: full-width Card or Map placeholder
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (info != null) {
                                Text(info.city, style = MaterialTheme.typography.headlineSmall)
                            }
                            if (info != null) {
                                Text(info.description)
                            }
                            if (info != null) {
                                Text("${info.tempCelsius} °C")
                            }

                            // If you add a Map, make it fill remaining space
                            // Example using a Box placeholder:
//                            Box(
//                                modifier = Modifier
//                                    .weight(1f) // takes all remaining vertical space
//                                    .fillMaxWidth()
//                                    .background(Color.Gray),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                //Text("Map goes here", color = Color.White)
//                            }
                        }
                    }
                }
            }
        }
    }
}

