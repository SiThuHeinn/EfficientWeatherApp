package com.sithuheinn.mm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [WeatherDataEntity::class],
    version = 1
)
abstract class WeatherAppDatabase: RoomDatabase() {

    abstract fun getWeatherDao(): WeatherDao

}