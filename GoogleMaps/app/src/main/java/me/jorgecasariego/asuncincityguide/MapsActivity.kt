package me.jorgecasariego.asuncincityguide

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    // Paso 6:
    // const val: https://stackoverflow.com/a/37596023/2091181
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        // Paso 20
        private const val REQUEST_CHECK_SETTINGS = 2

        // Paso 24
        private const val PLACE_PICKER_REQUEST = 3

    }

    // Paso 9
    private lateinit var lastLocation: Location

    // Paso 19
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false

    // Paso 7
    private fun setupMap() {
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)

            return
        }

        // Paso 11: esto hace que se pueda dibujar un punto azul sobre la ubicación del usuario
        // Esto tambien agrega un boton en el mapa, que cuando se pulsa, centra el mapa en la ubicación del usuario
        mMap.isMyLocationEnabled = true

        // Paso 17
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN

        // Paso 12:
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            // // Obtenemos la ultima ubicación conocida. En algunos casos esto puede llegar a ser null
            if (location != null) {
                lastLocation = location
                var currenLatLng = LatLng(location.latitude, location.longitude)

                // Paso 14: Añadimos un marcador a la posición actual del usuario
                placeMarkerOnMap(currenLatLng)

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currenLatLng, 12f))
            }
        }

    }

    // Paso 3
    override fun onMarkerClick(p0: Marker?) = false

    private lateinit var mMap: GoogleMap

    // Paso 1: Añadimos FusedLocationProviderClient
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Paso 2
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                super.onLocationResult(p0)

                lastLocation = p0.lastLocation
                placeMarkerOnMap(LatLng(lastLocation.latitude, lastLocation.longitude))
            }
        }

        createLocationRequest()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            loadPlacePicker()
        }
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

        // Paso 4: Habilitamos el zoom y hacemos que esta actividad sea la encargada de manejar
        // los clicks en el marker
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)

        // Paso 10: Eliminamos todos los markes
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        // Paso 5: agregamos un nuevo marcador
        val myPlace = LatLng(-25.28, -57.63)  // Agregamos asuncion
        mMap.addMarker(MarkerOptions().position(myPlace).title("Asunción, Madre de Ciudades"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, 12.0f))

        // Paso 8
        setupMap()
    }

    // Paso 13: añadimos un metodo para agregar marcadores al mapa
    private fun placeMarkerOnMap(location: LatLng) {
        val markerOptions = MarkerOptions().position(location)

        // Paso 15: agregar nuestros propios iconos
        //markerOptions.icon(BitmapDescriptorFactory.fromBitmap(
                //BitmapFactory.decodeResource(resources, R.mipmap.ic_user_location)))

        // Paso 16: Extra: Cambiar el color del icono
        //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))

        // Paso 19
        val nombreDireccion = getDireccion(location)
        markerOptions.title(nombreDireccion)
        mMap.addMarker(markerOptions)
    }

    // Paso 18: Mostrar la dirección del marcador
    fun getDireccion(latLng: LatLng): String{
        // 1. Creamos un objeto Geocoder el cual convierte las coordenadas de una latitud, longitud
        // en direcciones y vice versa
        val geocoder = Geocoder(this)
        val direcciones: List<Address>?
        val direccion: Address?
        var direccionTexto = ""

        try {
            // 2: Obtenemos las direcciones de la latitud y longitud
            direcciones = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)

            // 3 Si la respueta tiene alguna direccion, concatenamos a un string y retornamos
            if (null != direcciones && !direcciones.isEmpty()) {
                direccion = direcciones[0]
                if (direccion.maxAddressLineIndex == 0) direccionTexto = direccion.getAddressLine(0)
                else {
                    for (i in 0 until direccion.maxAddressLineIndex) {
                        direccionTexto += if (i == 0) direccion.getAddressLine(i) else "\n" + direccion.getAddressLine(i)
                    }
                }
            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }

        return direccionTexto
    }

    //*** DESDE AQUI PARA ABAJO ES PARA OBTENER ACTUALIZACIONES CONSTANTES DEL USUARIO
    // Paso 21
    private fun startLocationUpdates() {
        //1
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        //2
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
    }

    // paso 22
    private fun createLocationRequest() {
        // 1
        locationRequest = LocationRequest()
        // 2
        locationRequest.interval = 10000
        // 3
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

        // 4
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        // 5
        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            // 6
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(this@MapsActivity,
                            REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    // Paso 23
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
        }

        // PASO 26:
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place = PlacePicker.getPlace(this, data)
                var addressText = place.name.toString()
                addressText += "\n" + place.address.toString()

                placeMarkerOnMap(place.latLng)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    public override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }

    // PASO 25: METODOS PARA BUSCAR LUGARES
    private fun loadPlacePicker() {
        val builder = PlacePicker.IntentBuilder()

        try {
            startActivityForResult(builder.build(this@MapsActivity), PLACE_PICKER_REQUEST)
        } catch (e: GooglePlayServicesRepairableException) {
            e.printStackTrace()
        } catch (e: GooglePlayServicesNotAvailableException) {
            e.printStackTrace()
        }
    }

}
