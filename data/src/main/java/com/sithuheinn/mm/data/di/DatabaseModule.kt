package com.sithuheinn.mm.data.di

import android.content.Context
import androidx.room.Room
import com.sithuheinn.mm.data.database.WeatherAppDatabase
import com.sithuheinn.mm.data.database.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherAppDatabase {
        return Room.databaseBuilder(
            context,
            WeatherAppDatabase::class.java,
            "weather_db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideWeatherDao(database: WeatherAppDatabase): WeatherDao {
        return database.getWeatherDao()
    }
}