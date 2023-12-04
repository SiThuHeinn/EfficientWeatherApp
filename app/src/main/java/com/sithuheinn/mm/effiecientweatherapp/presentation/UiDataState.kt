package com.sithuheinn.mm.effiecientweatherapp.presentation

import com.sithuheinn.mm.domain.model.DisplayableWeatherDataModel


/**
 * Data class representing the state of the UI related to weather data.
 *
 * @property displayableWeatherDataModel The displayable weather data model to be presented in the UI.
 * @property isLoading A flag indicating whether the UI is currently in a loading state.
 * @property error A string containing an error message in case of an issue, or null if there is no error.
 */
data class UiDataState(
    val displayableWeatherDataModel: DisplayableWeatherDataModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)