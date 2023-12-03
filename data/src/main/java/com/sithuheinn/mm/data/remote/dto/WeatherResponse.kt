package com.sithuheinn.mm.data.remote.dto

import com.squareup.moshi.Json

data class WeatherResponse(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataResponse
)