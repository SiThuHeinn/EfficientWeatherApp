package com.sithuheinn.mm.domain.repository

import com.sithuheinn.mm.domain.Resource
import com.sithuheinn.mm.domain.model.DisplayableWeatherDataModel


/**
 * Interface defining the contract for accessing weather-related data from a repository.
 * It declares a suspended function to asynchronously fetch weather data based on
 * geographical coordinates (latitude and longitude).
 *
 * @see Resource A sealed class representing the result of the weather data retrieval operation,
 *                encapsulating either a successful outcome with a [DisplayableWeatherDataModel] or
 *                an error with an associated message.
 *
 * @param lat The latitude coordinate for the location of interest.
 * @param long The longitude coordinate for the location of interest.
 * @return A [Resource] containing either a [DisplayableWeatherDataModel] on success or an error
 *         message on failure.
 */
interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, long: Double): Resource<DisplayableWeatherDataModel>
}