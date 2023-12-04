package com.sithuheinn.mm.location.provider

import android.location.Location

/**
 * Interface defining a location provider contract for obtaining the current location.
 * Implementations of this interface should provide the functionality to retrieve
 * the current geographical location in a suspendable manner.
 */
interface LocationProvider {

    /**
     * Suspend function to asynchronously fetch the current location.
     *
     * @return A [Location] object representing the current geographical location,
     *         or null if the location information is unavailable or permission is denied.
     */
    suspend fun getCurrentLocation(): Location?
}
