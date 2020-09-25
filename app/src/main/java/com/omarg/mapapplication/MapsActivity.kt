package com.omarg.mapapplication


import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.omarg.mapapplication.Utilidades.UtilidadesPermisos
import com.omarg.mapapplication.Utilidades.UtilidadesUI
import javax.security.auth.callback.Callback


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnSuccessListener<Location> {

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView
    private var CODE_REQUEST_PERMISSION_LOCATION = 0
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var layoutMain: View
    private lateinit var locationRequest: LocationRequest
    private lateinit var callBackLocation: LocationCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        layoutMain = findViewById(R.id.layout_main_activity)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapView = findViewById(R.id.map)
        mapView.onCreate(null)
        mapView.onResume()
        mapView.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (!UtilidadesPermisos.checkPermissionLocation(this)) {
            CODE_REQUEST_PERMISSION_LOCATION = UtilidadesPermisos.pedirPermisosLocation(this)

        } else {
            inicializarLocation()
            callback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    super.onLocationResult(locationResult)
                    for (ubicacion in locationResult?.locations!!) {
                        UtilidadesUI.snackSuccessful(
                            "Su nueva ubicación: ${ubicacion.longitude},${ubicacion.latitude}",
                            layoutMain,
                            applicationContext,
                            Snackbar.LENGTH_INDEFINITE
                        ).show()
                    }
                }
            }
            fusedLocationClient.requestLocationUpdates(locationRequest, callback, null)
        }
    }

    fun configureCamera(position: LatLng): CameraPosition? {
        val camera = CameraPosition.Builder()
            .zoom(12f)
            .bearing(0f)
            .target(position)
            .tilt(45f)
            .build()

        return camera
    }

    lateinit var callback: LocationCallback

    private fun inicializarLocation() {
        locationRequest = LocationRequest()
        locationRequest.interval = 10 * 1000
        locationRequest.fastestInterval = 5 * 1000
        locationRequest.priority = LocationRequest.PRIORITY_LOW_POWER
    }

    override fun onSuccess(location: Location?) {
        val view: View = getWindow().getDecorView().findViewById(android.R.id.content)
        if (location != null) {
            val mensajeToast =
                "Mi latitud: ${location.latitude} \nMi longitud: ${location.longitude}"
            Toast.makeText(this, mensajeToast, Toast.LENGTH_SHORT).show()
            val mensaje = "La localización se obtuvo con éxito"
            UtilidadesUI.snackSuccessful(mensaje, view, this, Snackbar.LENGTH_INDEFINITE).show()
            val latLang = LatLng(location.latitude, location.longitude)
            val camera = configureCamera(latLang)
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera))
            mMap.addMarker(MarkerOptions().position(latLang).title("My position"))
        } else {
            val mensaje = "No se pudo obtener la ubicación"
            UtilidadesUI.snackWarning(mensaje, view, this, Snackbar.LENGTH_INDEFINITE).show()
        }
    }


}
