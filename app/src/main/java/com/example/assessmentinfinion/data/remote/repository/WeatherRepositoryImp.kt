package com.example.assessmentinfinion.data.remote.repository

// WeatherRepositoryImpl.kt

import android.util.Log
import com.example.assessmentinfinion.data.remote.remote.OpenWeatherApi
import com.example.assessmentinfinion.model.WeatherInfo
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi,
    private val apiKey: String
) : WeatherRepository {

    override suspend fun getWeather(city: String): Result<WeatherInfo> = try {
        val dto = api.getWeather(city, apiKey)
        Log.d("WeatherAPI", dto.toString())
        val info = WeatherInfo(
            city = dto.name ?: city,
            description = dto.weather?.firstOrNull()?.description ?: "N/A",
            tempCelsius = dto.main?.temp ?: Double.NaN
        )
        Result.success(info)
    } catch (e: Exception) {
        Log.e("WeatherAPI", "Failed to fetch weather", e)
        Result.failure(e)
    }
}

