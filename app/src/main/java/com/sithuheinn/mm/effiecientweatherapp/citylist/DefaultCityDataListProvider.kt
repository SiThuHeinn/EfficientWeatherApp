package com.sithuheinn.mm.effiecientweatherapp.citylist

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultCityDataListProvider @Inject constructor(
    @ApplicationContext private val context: Context
): CityDataListProvider {

    private val moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(List::class.java, CityModel::class.java)


    override fun getAllCityList(): List<CityModel> {
        val jsonString = readFromJsonFile()
        if (jsonString.isEmpty()) return emptyList()

        val adapter: JsonAdapter<List<CityModel>> = moshi.adapter(listType)
        return adapter.fromJson(jsonString).orEmpty()
    }

    private fun readFromJsonFile(): String {
        return try {
            context.assets.open("cities.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            ""
        }
    }
}