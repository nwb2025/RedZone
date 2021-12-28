package com.example.redzone

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.redzone.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.*
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener

import com.google.android.gms.maps.model.Marker




class MapsActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

        // Add a marker in Kirovsky and move the camera
        val kirovsky = LatLng(59.8952, 31.4209)
        val jbi = LatLng(56.8352, 60.6848)
        val akadem = LatLng(56.7871, 60.5116)

       mMap.setOnMarkerClickListener {
           Toast.makeText(this@MapsActivity, "Clicked location is ${it.title}", Toast.LENGTH_SHORT)
               .show()
            Log.d("hello", "onMarkerClick: " + it.title)
            true }

        mMap.addMarker(MarkerOptions().position(kirovsky)
            .title("Marker in Kirovsky ")
            .icon(bitmapDescFromVector(applicationContext, R.drawable.ic_marker)))
        mMap.addMarker(MarkerOptions().position(jbi)
            .title("Marker in ZBI ")
            .icon(bitmapDescFromVector(applicationContext, R.drawable.ic_marker)))
        mMap.addMarker(MarkerOptions().position(akadem)
            .title("Marker in Akadem ")
            .icon(bitmapDescFromVector(applicationContext, R.drawable.ic_marker)))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kirovsky))


    }
    private fun  bitmapDescFromVector(contex: Context,vectorResId:Int):BitmapDescriptor{
        val vectorDrawable: Drawable? = ContextCompat.getDrawable(contex,vectorResId)
        vectorDrawable!!.setBounds(0,0,vectorDrawable!!.intrinsicWidth,
            vectorDrawable.intrinsicHeight)
        val bitmap:Bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,Bitmap.Config.ARGB_8888)
        val canvas:Canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)

    }




}