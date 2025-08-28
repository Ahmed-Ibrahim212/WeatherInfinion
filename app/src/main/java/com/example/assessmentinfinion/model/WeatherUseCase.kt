package com.example.assessmentinfinion.model

import com.example.assessmentinfinion.data.remote.remote.WeatherResponse
import com.example.assessmentinfinion.data.remote.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String) = repository.getWeather(city)
}