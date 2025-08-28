package com.example.assessmentinfinion.data.remote.remote

import com.example.assessmentinfinion.data.remote.remote.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface OpenWeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): WeatherResponse
}
