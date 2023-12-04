package com.sithuheinn.mm.effiecientweatherapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sithuheinn.mm.domain.Resource
import com.sithuheinn.mm.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherInformationScreenViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    init {
        viewModelScope.launch {
            val data = repository.getWeatherData(16.771, 96.1993)
            when(data) {
                is Resource.Error -> {
                    Log.d("WeatherVM", "${data.message}")
                }

                is Resource.Success -> {
                    Log.d("WeatherVM", "${data.data?.currentWeatherData}")
                }
            }
        }
    }
}