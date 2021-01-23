package org.mousehole.a4thkotlinapp

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class RestaurantLocation : AppCompatActivity(), OnMapReadyCallback, LocationListener {


    private lateinit var locationManager: LocationManager

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_location)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000L, 2f,this)

    }


    override fun onStop() {
        super.onStop()
        locationManager.removeUpdates(this)
    }





    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

    }

    override fun onLocationChanged(location: Location) {
        Log.d("TAG_X", "${location.latitude}, ${location.longitude}")
        if(this::mMap.isInitialized){
            val currenLocation = LatLng(location.latitude, location.longitude)
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(currenLocation).title("Marker in Enhance IT"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currenLocation))
        }
    }
}