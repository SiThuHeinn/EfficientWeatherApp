package com.sithuheinn.mm.data.repository

import com.sithuheinn.mm.data.remote.RemoteResource
import com.sithuheinn.mm.data.remote.api.WeatherApi
import com.sithuheinn.mm.data.remote.mapper.transformToDisplayableWeatherDataModel
import com.sithuheinn.mm.data.remote.safeApiCall
import com.sithuheinn.mm.domain.Resource
import com.sithuheinn.mm.domain.model.DisplayableWeatherDataModel
import com.sithuheinn.mm.domain.repository.WeatherRepository
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val apkService: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(
        lat: Double,
        long: Double
    ): Resource<DisplayableWeatherDataModel> {
        return when (val response = safeApiCall { apkService.getWeatherData(lat, long)  }) {
            is RemoteResource.SuccessEvent -> {
                Resource.Success(response.data?.transformToDisplayableWeatherDataModel())
            }

            is RemoteResource.ErrorEvent -> {
                Resource.Error(response.getErrorMessage())
            }

            else -> {
                Resource.Error(response.getErrorMessage())
            }
        }
    }
}