package com.sithuheinn.mm.location.provider

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume


/**
 * Default implementation of the [LocationProvider] interface, utilizing the FusedLocationProviderClient
 * for obtaining the current location asynchronously. This implementation checks for location permissions
 * and GPS availability before attempting to retrieve the location.
 *
 * @property locationClient FusedLocationProviderClient for retrieving location information.
 * @property application The Android Application context.
 */


@ExperimentalCoroutinesApi
class DefaultLocationProvider @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
): LocationProvider {


    /**
     * Suspend function to asynchronously fetch the current location.
     *
     * @return A [Location] object representing the current geographical location,
     *         or null if the location information is unavailable or permission is denied.
     */
    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        // Return null if permissions or GPS is not available
        if(!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled) {
            return null
        }

        // Use suspendCancellableCoroutine to wrap the location retrieval logic
        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if(isComplete) {
                    if(isSuccessful) {
                        cont.resume(result)
                    } else {
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnFailureListener {
                    cont.resume(null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}