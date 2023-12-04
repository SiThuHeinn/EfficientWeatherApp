package com.sithuheinn.mm.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weather_data")
data class WeatherDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val jsonString: String
)