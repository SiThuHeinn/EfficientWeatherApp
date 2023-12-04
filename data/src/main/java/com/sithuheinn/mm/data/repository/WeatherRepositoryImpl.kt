package com.sithuheinn.mm.data.repository

import com.sithuheinn.mm.data.remote.RemoteResource
import com.sithuheinn.mm.data.remote.api.WeatherApi
import com.sithuheinn.mm.data.remote.dto.WeatherResponse
import com.sithuheinn.mm.data.remote.mapper.transformToDisplayableWeatherDataModel
import com.sithuheinn.mm.data.remote.safeApiCall
import com.sithuheinn.mm.domain.Resource
import com.sithuheinn.mm.domain.model.DisplayableWeatherDataModel
import com.sithuheinn.mm.domain.repository.WeatherRepository
import com.squareup.moshi.Moshi
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val apkService: WeatherApi
) : WeatherRepository {

    private val moshi = Moshi.Builder().build()

    override suspend fun getWeatherData(
        lat: Double,
        long: Double
    ): Resource<DisplayableWeatherDataModel> {
        return when (val response = safeApiCall { apkService.getWeatherData(lat, long)  }) {
            is RemoteResource.SuccessEvent -> {
                val adapter = moshi.adapter(WeatherResponse::class.java)
                val jsonString = adapter.toJson(response.data)
                Resource.Success(
                    response.data?.transformToDisplayableWeatherDataModel()
                )
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