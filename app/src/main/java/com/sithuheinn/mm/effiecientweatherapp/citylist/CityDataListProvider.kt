package com.sithuheinn.mm.effiecientweatherapp.citylist


/**
 * Interface representing a data provider for city information.
 */
interface CityDataListProvider {
    fun getAllCityList(): List<CityModel>
}