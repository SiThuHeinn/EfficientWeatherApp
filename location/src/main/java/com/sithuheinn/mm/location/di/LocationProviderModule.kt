package com.sithuheinn.mm.location.di

import com.sithuheinn.mm.location.provider.DefaultLocationProvider
import com.sithuheinn.mm.location.provider.LocationProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


/**
 * Dagger Hilt module for providing a dependency injection binding for the [LocationProvider] interface.
 * This module specifies the concrete implementation [DefaultLocationProvider] to be used as the
 * primary location provider within the application.
 */
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class LocationProviderModule {

    /**
     * Binds the [DefaultLocationProvider] implementation to the [LocationProvider] interface.
     *
     * @param impl The default location provider implementation.
     * @return An instance of [LocationProvider] for dependency injection.
     */
    @Binds
    @Singleton
    abstract fun bindLocationProvider(impl: DefaultLocationProvider): LocationProvider
}
