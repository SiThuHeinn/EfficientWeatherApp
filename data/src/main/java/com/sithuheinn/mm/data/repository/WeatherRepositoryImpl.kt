package com.sithuheinn.mm.data.repository

import com.sithuheinn.mm.data.database.WeatherDao
import com.sithuheinn.mm.data.database.WeatherDataEntity
import com.sithuheinn.mm.data.remote.RemoteResource
import com.sithuheinn.mm.data.remote.api.WeatherApi
import com.sithuheinn.mm.data.remote.dto.WeatherResponse
import com.sithuheinn.mm.data.remote.mapper.transformToDisplayableWeatherDataModel
import com.sithuheinn.mm.data.remote.safeApiCall
import com.sithuheinn.mm.domain.Resource
import com.sithuheinn.mm.domain.model.DisplayableWeatherDataModel
import com.sithuheinn.mm.domain.repository.WeatherRepository
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val apkService: WeatherApi,
    private val dao: WeatherDao
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
                withContext(Dispatchers.IO) {
                    dao.clear()
                    dao.insert(WeatherDataEntity(jsonString = jsonString))
                }
                Resource.Success(
                    response.data?.transformToDisplayableWeatherDataModel()
                )
            }

            is RemoteResource.ErrorEvent -> {
                if(response.message == RemoteResource.CustomMessages.ServerError) {
                    val entity = dao.getWeatherData()
                    if (entity == null) {
                        Resource.Error(response.getErrorMessage())
                    } else {
                        val adapter = moshi.adapter(WeatherResponse::class.java)
                        val dto = adapter.fromJson(entity.jsonString)
                        Resource.Success(
                            dto?.transformToDisplayableWeatherDataModel()
                        )
                    }
                } else {
                    Resource.Error(response.getErrorMessage())
                }
            }

            else -> {
                Resource.Error(response.getErrorMessage())
            }
        }
    }
}