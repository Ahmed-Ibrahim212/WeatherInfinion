package com.example.assessmentinfinion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessmentinfinion.data.remote.remote.WeatherResponse
import com.example.assessmentinfinion.model.GetWeatherUseCase
import com.example.assessmentinfinion.model.UiState
import com.example.assessmentinfinion.model.WeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getWeather: GetWeatherUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState<WeatherInfo>())
    val state: StateFlow<UiState<WeatherInfo>> = _state

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun load(city: String) {
        _state.value = UiState(loading = true)
        viewModelScope.launch {
            val res = getWeather(city)
            _state.value = res.fold(
                onSuccess = { UiState(data = it) },
                onFailure = {
                    _errorMessage.value = it.message ?: "Unable to fetch weather"
                    UiState(error = it.message ?: "Error")
                }
            )
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}