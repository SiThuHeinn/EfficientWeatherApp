package com.sithuheinn.mm.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Weather data response
 *
 * In this project, [MoshiConverterFactory] is used to parsed to response from api call.
 * @property time
 * @property temperatures
 * @property weatherCodes
 * @property pressures
 * @property windSpeeds
 * @property humidities
 * @constructor Create empty Weather data response
 */
@JsonClass(generateAdapter = true)
data class WeatherDataResponse(
    val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>,
    @field:Json(name = "windspeed_10m")
    val windSpeeds: List<Double>,
    @field:Json(name = "relativehumidity_2m")
    val humidities: List<Double>
)