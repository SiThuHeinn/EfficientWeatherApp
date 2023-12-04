package com.sithuheinn.mm.data.remote.dto

import com.squareup.moshi.Json

/**
 * Weather response
 *
 * Hourly weather data is focused on this app.
 * @property weatherData
 */
data class WeatherResponse(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataResponse
)