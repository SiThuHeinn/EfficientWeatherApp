package com.sithuheinn.mm.effiecientweatherapp.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sithuheinn.mm.data.database.WeatherAppDatabase
import com.sithuheinn.mm.data.database.WeatherDao
import com.sithuheinn.mm.data.database.WeatherDataEntity
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WeatherAppDatabaseTest: TestCase() {

    private lateinit var database: WeatherAppDatabase
    private lateinit var dao: WeatherDao


    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, WeatherAppDatabase::class.java).build()
        dao = database.getWeatherDao()
    }

    @Test
    fun insert_and_get_data_must_be_ok() = runBlocking {
        val entity = WeatherDataEntity(jsonString = "This is testing String.")
        dao.insert(entity)
        val result = dao.getWeatherData()
        assertEquals(entity.jsonString, result?.jsonString)
    }

    @Test
    fun clear_database_must_be_empty() = runBlocking {
        dao.clear()
        val result = dao.getWeatherData()
        assertNull(result)
    }

    @After
    fun closeDatabase(){
        database.close()
    }
}