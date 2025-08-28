package com.example.assessmentinfinion.data.remote.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    val name: String?,
    val main: Main?,
    val weather: List<WeatherDescription>?
)

@JsonClass(generateAdapter = true)
data class Main(val temp: Double?)

@JsonClass(generateAdapter = true)
data class WeatherDescription(val description: String?)
