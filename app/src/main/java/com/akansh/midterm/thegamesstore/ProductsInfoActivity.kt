package com.akansh.midterm.thegamesstore

import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import java.lang.Exception
import java.util.*

class ProductsInfoActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var sellerName: TextView
    private lateinit var sellerAddress: TextView
    private lateinit var okButton: AppCompatButton
    private var mGoogleMap: GoogleMap? = null
    private val LAT = "Latitude"
    private val LONG = "Longitude"
    private var lat: Double = 0.0
    private var longitude: Double = 0.0
    private val SELLER_NAME = "SellerName"
    private val SELLER_LOCATION = "SellerLocation"
    private lateinit var sellerNameString: String
    private lateinit var sellerLocation: String
    private lateinit var geocoder: Geocoder
    private lateinit var address: List<Address>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_info)

        geocoder = Geocoder(this, Locale.getDefault())
        sellerName = findViewById(R.id.sellerName)
        sellerAddress = findViewById(R.id.sellerAddress)
        okButton = findViewById(R.id.ok)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        lat = intent.getDoubleExtra(LAT, 0.0)
        longitude = intent.getDoubleExtra(LONG, 0.0)
        sellerNameString = intent.getStringExtra(SELLER_NAME)
        sellerLocation = intent.getStringExtra(SELLER_LOCATION)
        try {
            address = geocoder.getFromLocation(lat, longitude, 1)
            sellerName.text = sellerNameString
            sellerAddress.text = address[0].getAddressLine(0)
        } catch (ex: Exception) {
            this.finish()
            Toast.makeText(this@ProductsInfoActivity, "Some problem occurred. Please try again later", Toast.LENGTH_SHORT).show()
        }
        okButton.setOnClickListener {
            this.finish()
        }
    }

    override fun onMapReady(gMap: GoogleMap?) {
        mGoogleMap = gMap

        val latLng = LatLng(lat, longitude)
        mGoogleMap?.addMarker(MarkerOptions().position(latLng).title(sellerNameString))
        mGoogleMap?.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mGoogleMap?.setMinZoomPreference(5f)
        mGoogleMap?.setMaxZoomPreference(20f)
    }
}
