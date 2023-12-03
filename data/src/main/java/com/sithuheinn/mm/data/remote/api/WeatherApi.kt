package com.sithuheinn.mm.data.remote.api

import com.sithuheinn.mm.data.remote.dto.WeatherDataResponse
import com.sithuheinn.mm.data.remote.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherResponse
}