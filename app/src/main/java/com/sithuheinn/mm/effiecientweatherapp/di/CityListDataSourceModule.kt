package com.sithuheinn.mm.effiecientweatherapp.di

import com.sithuheinn.mm.effiecientweatherapp.citylist.CityDataListProvider
import com.sithuheinn.mm.effiecientweatherapp.citylist.DefaultCityDataListProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class CityListDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindCityDataListProvider(impl: DefaultCityDataListProvider): CityDataListProvider
}