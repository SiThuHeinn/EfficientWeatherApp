package com.sithuheinn.mm.domain.model

import org.threeten.bp.LocalDateTime

/**
 * Weather data model
 *
 * [WeatherDataModel] is used to display per weather info.
 *
 * @property time  time unit for weather
 * @property temperatureCelsius
 * @property pressure
 * @property windSpeed
 * @property humidity
 * @property weatherCondition
 * @constructor Create empty Weather data model
 */
data class WeatherDataModel(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherCondition: WeatherCondition
)