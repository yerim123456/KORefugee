package com.example.korefugee.Map


import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.korefugee.Login.LoginActivity
import com.example.korefugee.R
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_map.*
import java.io.IOException
import java.util.*


class MapActivity : AppCompatActivity(), OnMapReadyCallback
{


    lateinit var accesstoken:String
    lateinit var refreshtoken:String

    var latitude:Double = 123.0
    var longtitude:Double = 232.0

    // GoogleApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        if (intent.hasExtra("accesstoken") && intent.hasExtra("refreshtoken")) {
            accesstoken = intent.getStringExtra("accesstoken").toString()
            refreshtoken =  intent.getStringExtra("refreshtoken").toString()
        }

        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.layout_map, mapFragment)
            .commit()
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        // 이메일 및 비밀번호 받아오기
        if (intent.hasExtra("latitude") && intent.hasExtra("longtitude")) {
            latitude = intent.getStringExtra("latitude")!!.toDouble()
            longtitude = intent.getStringExtra("longtitude")!!.toDouble()

        }
        // Set the map coordinates to Kyoto Japan.
        val seoul = LatLng(37.566510,126.978403)

        // Add a marker on the map coordinates.
        val position = CameraPosition.Builder()
            .target(seoul)
            .zoom(16f)
            .build()

        googleMap?.moveCamera((CameraUpdateFactory.newCameraPosition(position)))
    }


}