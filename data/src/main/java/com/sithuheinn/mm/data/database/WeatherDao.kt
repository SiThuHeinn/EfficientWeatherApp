package com.sithuheinn.mm.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WeatherDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: WeatherDataEntity)

    @Query("SELECT * FROM weather_data")
    suspend fun getWeatherData(): WeatherDataEntity?

    @Query("DELETE FROM weather_data")
    fun clear()
}