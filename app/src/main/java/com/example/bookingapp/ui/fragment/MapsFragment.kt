package com.example.bookingapp.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookingapp.R
import com.example.bookingapp.ui.viewmodel.LocationViewModel
import com.example.bookingapp.utils.GpsUtils
import com.example.bookingapp.utils.LOCATION_REQUEST
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_maps.*

class MapsFragment : Fragment() {

    private lateinit var locationViewModel: LocationViewModel
    lateinit var mMaps: GoogleMap

    private val callback = OnMapReadyCallback { googleMap ->
        mMaps = googleMap
    }

    private var isGPSEnabled = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        GpsUtils(requireContext()).turnGpsOn(object : GpsUtils.OnGpsListener {
            override fun gpsStatus(isGPSEnable: Boolean) {
                isGPSEnabled = isGPSEnable
            }
        })
    }

    private fun startLocationUpdate() {
        locationViewModel.getLocationData().observe(viewLifecycleOwner, {
            latLong.text = ("${it.latitude} - + ${it.longitude}")
            Toast.makeText(requireContext(), "this location is ${it.latitude} - ${it.longitude}", Toast.LENGTH_SHORT).show()
            val current = LatLng(it.latitude, it.longitude)
            mMaps.addMarker(
                MarkerOptions()
                    .position(current)
                    .zIndex(10f)
                    .title("Current Location")
            )
            mMaps.mapType = (GoogleMap.MAP_TYPE_HYBRID)
            mMaps.isBuildingsEnabled = false
            mMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(current, 10f))
            mMaps.animateCamera(CameraUpdateFactory.zoomTo(15f), 2000, null)
        })
    }

    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }


    private fun invokeLocationAction() {
        when {
            !isGPSEnabled -> latLong.text = getString(R.string.enable_gps)

            isPermissionsGranted() -> startLocationUpdate()

            shouldShowRequestPermissionRationale() -> latLong.text = getString(R.string.permission_request)

            else -> ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_REQUEST
            )
        }
    }

    private fun shouldShowRequestPermissionRationale() =
        ActivityCompat.shouldShowRequestPermissionRationale(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) && ActivityCompat.shouldShowRequestPermissionRationale(
            requireActivity(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_REQUEST -> {
                invokeLocationAction()
            }
        }
    }

    private fun isPermissionsGranted() =
        ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
}