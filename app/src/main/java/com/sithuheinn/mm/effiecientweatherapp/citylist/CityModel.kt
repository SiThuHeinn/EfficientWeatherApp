package com.sithuheinn.mm.effiecientweatherapp.citylist

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class CityModel(
    val name: String,
    val lat: String,
    val lng: String
)
