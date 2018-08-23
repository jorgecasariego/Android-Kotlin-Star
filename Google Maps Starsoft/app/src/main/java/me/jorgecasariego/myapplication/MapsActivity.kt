package me.jorgecasariego.myapplication

import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private fun configurarMapa() {
        // Si no tenemos permiso para el ACCESS FINE LOCATION le msotramo al usuario una ventana
        // para habilitar el servicio
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)

            return
        }

        mMap.isMyLocationEnabled = false
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            // Obtenemos la ultima ubicacion conocida. En algunos casos esto puede llegar a dar null
            if (location != null) {
                lastLocation = location
                var latitudLongitudActual = LatLng(location.latitude, location.longitude)
                colocarMarcador(latitudLongitudActual)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudLongitudActual, 16f))
            }

        }
    }

    private fun colocarMarcador(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.ic_map)
        ))
        mMap.addMarker(markerOptions)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults.get(0) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "EL usuario ha dado permiso para usar el mapa", Toast.LENGTH_SHORT).show()
                configurarMapa()
            } else {
                Toast.makeText(this, "EL usuario no ha dado permiso para usar el mapa", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)

        configurarMapa()
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        Toast.makeText(this, "Click sobre marker", Toast.LENGTH_SHORT).show()
        return false
    }


}
