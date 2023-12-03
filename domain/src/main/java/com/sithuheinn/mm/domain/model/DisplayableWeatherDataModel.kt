package com.sithuheinn.mm.domain.model

/**
 * Displayable weather data model
 *
 * @property weatherDataPerDay  Map<Key: Int> indicate the day of the data. [e.g. 0 means today]
 * @property currentWeatherData current weather information to be displayed
 */
data class DisplayableWeatherDataModel(
    val weatherDataPerDay: Map<Int, List<WeatherDataModel>>,
    val currentWeatherData: WeatherDataModel?
)