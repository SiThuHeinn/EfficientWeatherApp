package com.sithuheinn.mm.effiecientweatherapp.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sithuheinn.mm.domain.Resource
import com.sithuheinn.mm.domain.repository.WeatherRepository
import com.sithuheinn.mm.effiecientweatherapp.citylist.CityDataListProvider
import com.sithuheinn.mm.effiecientweatherapp.citylist.CityModel
import com.sithuheinn.mm.location.provider.LocationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class responsible for managing and providing weather-related information to the UI.
 *
 * @property repository The repository for fetching weather data.
 * @property locationProvider The provider for obtaining the current location.
 */
@HiltViewModel
class WeatherInformationViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val cityDataListProvider: CityDataListProvider,
    private val locationProvider: LocationProvider
): ViewModel() {

    /**
     * State property representing the current UI state related to weather information.
     */
    var state by mutableStateOf(UiDataState())
        private set

    var city by mutableStateOf("")
        private set

    init {
        Log.d("WeatherVM", "initiated.")
    }

    fun getCityList(): List<CityModel> = cityDataListProvider.getAllCityList()

    fun fetchWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            locationProvider.getCurrentLocation()?.let { location ->
                when (val result = repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            displayableWeatherDataModel = result.data,
                            isLoading = false,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            displayableWeatherDataModel = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }

            } ?: kotlin.run {
                state = state.copy(isLoading = false, error = "Location data is not available. Please make sure to grant permissions and enable GPS.")
            }
        }
    }
    fun onLocationSelected(model: CityModel) {
        if (model.lat.isEmpty() || model.lng.isEmpty()) return
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            when (val result = repository.getWeatherData(model.lat.toDouble(), model.lng.toDouble())) {
                is Resource.Success -> {
                    city = model.name
                    state = state.copy(
                        displayableWeatherDataModel = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        displayableWeatherDataModel = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("WeatherVM", "onCleared")
    }

}