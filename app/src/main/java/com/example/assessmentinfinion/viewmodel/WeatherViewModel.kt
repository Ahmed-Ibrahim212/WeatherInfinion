package com.example.assessmentinfinion.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmentinfinion.model.GetWeatherUseCase
import com.example.assessmentinfinion.model.UiState
import com.example.assessmentinfinion.model.UserPrefs
import com.example.assessmentinfinion.model.WeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeather: GetWeatherUseCase
) : ViewModel() {

    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city

    private val _weather = MutableStateFlow(UiState<WeatherInfo>())
    val weather: StateFlow<UiState<WeatherInfo>> = _weather

    private val _validationError = MutableStateFlow<String?>(null)
    val validationError: StateFlow<String?> = _validationError

    fun onCityChange(newCity: String) {
        _city.value = newCity
        _validationError.value = null // clear validation error when typing
    }

    fun fetchWeather() {
        val current = _city.value.trim()
        if (current.isEmpty()) {
            _validationError.value = "Please enter a city name"
            return
        }

        _weather.value = UiState(loading = true)

        viewModelScope.launch {
            val result = getWeather(current)
            _weather.value = result.fold(
                onSuccess = { UiState(data = it) },
                onFailure = { UiState(error = "City not found or API error") }
            )
        }
    }
}
