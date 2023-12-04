package com.sithuheinn.mm.data.remote.mapper

import com.sithuheinn.mm.data.remote.dto.WeatherDataResponse
import com.sithuheinn.mm.data.remote.dto.WeatherResponse
import com.sithuheinn.mm.domain.model.DisplayableWeatherDataModel
import com.sithuheinn.mm.domain.model.WeatherCondition
import com.sithuheinn.mm.domain.model.WeatherDataModel
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * This Kotlin code defines a set of extension functions to transform weather-related data
 * from DTOs (Data Transfer Objects) to more structured representations. It includes the
 * following main components:
 *
 * 1. [IndexWeatherData] data class: Represents [WeatherDataModel] with an associated index.
 *
 * 2. Extension function [transformAsMap] on [WeatherDataResponse]: Converts a DTO containing
 *    time-series weather data into a Map, grouping it by day and mapping it to lists of
 *    `WeatherDataModel` instances. The conversion involves extracting various weather-related
 *    parameters and applying appropriate conversions.
 *
 * 3. Extension function [transformToDisplayableWeatherDataModel] on [WeatherResponse]: Converts a DTO containing weather
 *    data into a [DisplayableWeatherDataModel] object. It utilizes the [transformAsMap] function to
 *    structure the weather data by day. Additionally, it determines the current weather
 *    data based on the current time and associates it with the `WeatherInfo` instance.
 *
 * Note: The code assumes the existence of other classes like [WeatherDataModel], [WeatherCondition],
 * and [DisplayableWeatherDataModel], which are not provided but are referenced in the code.
 */


private data class IndexWeatherData(
    val index: Int,
    val data: WeatherDataModel
)


fun WeatherDataResponse.transformAsMap(): Map<Int,List<WeatherDataModel>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexWeatherData(
            index = index,
            data = WeatherDataModel(
                time = time.toIsoDateTime(),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherCondition = WeatherCondition.fromWMO(weatherCode)

            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}


/**
 * Transforms a WeatherResponse object into a DisplayableWeatherDataModel, providing a more
 * structured representation for display purposes. It involves converting the weather data
 * into a map grouped by day and identifying the current weather data based on the current time.
 *
 * @return DisplayableWeatherDataModel containing organized weather data per day and the current
 *         weather data.
 */
fun WeatherResponse.transformToDisplayableWeatherDataModel(): DisplayableWeatherDataModel {
    val dataAsMap = weatherData.transformAsMap() // Transform the weather data into a map grouped by day.
    val now = LocalDateTime.now()

    // Find the current weather data by looking at the first day and matching the hour.
    val currentWeatherData = dataAsMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return DisplayableWeatherDataModel(
        weatherDataPerDay = dataAsMap,
        currentWeatherData = currentWeatherData
    )
}

/**
 * Extension function on String to convert it into a LocalDateTime object
 * using the ISO_DATE_TIME format. This function parses the input string,
 * assuming it represents a date and time in the ISO 8601 standard format.
 *
 * @return LocalDateTime object representing the parsed date and time.
 */
fun String.toIsoDateTime(): LocalDateTime = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)