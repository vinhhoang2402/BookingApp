package com.example.bookingapp.connection

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import androidx.lifecycle.LiveData
import com.example.bookingapp.ui.model.LocationModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationLiveData(context: Context) : LiveData<LocationModel>() {
    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    private fun setLocationData(location : Location) {
        value = LocationModel(
            longitude = location.longitude,
            latitude = location.latitude
        )
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.also {
                    setLocationData(it)
                }
            }
        startLocationUpdates()
    }

    override fun onInactive() {
        super.onInactive()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    companion object {
        val locationRequest: LocationRequest = LocationRequest.create().apply {
            interval = 5000
            fastestInterval = 1000
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }
    }
}