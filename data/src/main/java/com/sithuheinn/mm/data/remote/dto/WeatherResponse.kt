package com.sithuheinn.mm.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Weather response
 *
 * Hourly weather data is focused on this app.
 * @property weatherData
 */
@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataResponse
)