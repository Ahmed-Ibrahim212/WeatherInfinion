package com.example.assessmentinfinion.data.remote.repository

import com.example.assessmentinfinion.data.remote.remote.WeatherResponse
import com.example.assessmentinfinion.model.WeatherInfo
import dagger.Provides

interface WeatherRepository {
    suspend fun getWeather(city: String): Result<WeatherInfo>
}
