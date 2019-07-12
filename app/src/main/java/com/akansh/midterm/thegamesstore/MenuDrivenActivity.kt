package com.akansh.midterm.thegamesstore

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_menu_driven.*
import kotlinx.android.synthetic.main.app_bar_menu_driven.*

class MenuDrivenActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var realm: Realm
    private val USERNAME = "username"
    private val USER_BOOL = "userBool"
    private lateinit var user: String
    private var userBool: Boolean = false
    private lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_driven)
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)
        constraintLayout = findViewById(R.id.constraintLayout)

        realm = Realm.getDefaultInstance()
        val results = realm.where(Products::class.java).findAll()
        if (results.isEmpty()) {
            addProductsToSystem((results.size+1), "God of War", "https://en.wikipedia.org/wiki/God_of_War_(2018_video_game)#/media/File:God_of_War_4_cover.jpg", 30.99, 0.00, "PS4 Game", "Sony Entertainment", "45.5017 73.5673")
            addProductsToSystem((results.size+2), "Farcry 5", "https://cdn4.spong.com/pack/f/a/farcry5447129l/_-Far-Cry-5-Xbox-One-_.jpg", 50.49, 0.00, "XBox Game", "Ubisoft Entertainment", "43.6532 79.3832")
            addProductsToSystem((results.size+3), "PUBG", "https://i.redd.it/fhmw07hz085z.jpg", 149.49, 70.00, "PC Game", "PLAYERUNKNOWN", "46.8139 71.2080")
            addProductsToSystem((results.size+4), "Chess", "https://en.wikipedia.org/wiki/File:Chess_board_opening_staunton.jpg", 90.99, 0.00, "Indoor Games", "Pintoo Sports", "51.0486 114.0708")
            addProductsToSystem((results.size+5), "Cricket", "https://cdn.shopify.com/s/files/1/2193/7211/products/gray-nicolls-cook-bat-ball-cricket-set-bats-sets-best-buy_310_1024x1024@2x.jpg", 399.99, 0.00, "Outdoor Games", "Pintoo Sports", "49.2827 123.1207")
        }
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        changeFragment(DashboardFragment())

        user = intent.getStringExtra(USERNAME)
        userBool = intent.getBooleanExtra(USER_BOOL, false)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val header: View = navView.getHeaderView(0)
        val username: TextView = header.findViewById(R.id.username)
        username.text = "Welcome $user"
        nav_view.setNavigationItemSelectedListener(this)

        if (userBool)
            Snackbar.make(constraintLayout, "Welcome back $user", Snackbar.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                changeFragment(DashboardFragment())
                toolbar.title = getString(R.string.home)
            }
            R.id.nav_products -> {
                changeFragment(ProductsFragment())
                toolbar.title = getString(R.string.products)
            }
            R.id.nav_seller_location -> {
                Snackbar.make(constraintLayout, "Seller Location viewed in product information", Snackbar.LENGTH_SHORT).show()
            }
            R.id.nav_settings -> {
                Snackbar.make(constraintLayout, "Work in Progress", Snackbar.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                this.finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun addProductsToSystem(id: Int, productName: String, productImagePath: String, productPrice: Double, productDiscount: Double, productType: String, sellerName: String, sellerLocation: String) {
        realm.beginTransaction()

        val newProduct = realm.createObject(Products::class.java, id)
        newProduct.setProductName(productName)
        newProduct.setProductImagePath(productImagePath)
        newProduct.setProductPrice(productPrice)
        newProduct.setProductDiscounts(productDiscount)
        newProduct.setProductType(productType)
        newProduct.setSellerName(sellerName)
        newProduct.setSellerLocation(sellerLocation)

        realm.commitTransaction()
    }

    private fun changeFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.addToBackStack(null)

        transaction.commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode==KeyEvent.KEYCODE_BACK)
            Snackbar.make(constraintLayout, "Kindly logout from menu..", Snackbar.LENGTH_SHORT).show()

        return false
    }
}
