package com.example.assessmentinfinion

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.assessmentinfinion.ui.theme.AssessmentINfinionTheme
import com.example.assessmentinfinion.view.DetailScreen
import com.example.assessmentinfinion.view.HomeScreen
import com.example.assessmentinfinion.viewmodel.DetailViewModel
import com.example.assessmentinfinion.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavHost() //  this handles Splash → Home → Detail
            }
        }
    }
}
