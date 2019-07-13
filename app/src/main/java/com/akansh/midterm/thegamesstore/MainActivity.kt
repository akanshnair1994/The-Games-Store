package com.akansh.midterm.thegamesstore

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.AppCompatButton
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var forgotPassword: TextView
    private lateinit var incorrectCredentials: TextView
    private lateinit var login: AppCompatButton
    private lateinit var register: TextView
    private val USERNAME = "username"
    private val USER_BOOL = "userBool"
    private lateinit var realm: Realm
    private lateinit var sharedPrefs: SharedPreferences
    private val PASSWORD = "Password"
    private val LOGOUT = "Logout"
    private var userLoggedOut = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        realm = Realm.getDefaultInstance()
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        forgotPassword = findViewById(R.id.forgotPassword)
        incorrectCredentials = findViewById(R.id.incorrectCredentials)
        login = findViewById(R.id.login)
        register = findViewById(R.id.register)
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        userLoggedOut = intent.getBooleanExtra(LOGOUT, false)

        forgotPassword.setOnClickListener {
            Toast.makeText(this@MainActivity, "Forgot Password is in progress", Toast.LENGTH_SHORT).show()
        }

        if (!userLoggedOut) {
            if (sharedPrefs.getString(USERNAME, "") != "") {
                val intent = Intent(this@MainActivity, MenuDrivenActivity::class.java)
                intent.putExtra(USERNAME, sharedPrefs.getString(USERNAME, ""))
                intent.putExtra(USER_BOOL, true)
                startActivity(intent)
                this.finish()
            }
        }

        login.setOnClickListener {
            if (username.text.isNotEmpty() || password.text.isNotEmpty()) {
                incorrectCredentials.visibility = View.INVISIBLE

                val result = realm.where(Users::class.java).findAll()

                if (result.isNotEmpty()) {
                    for (user: Users in result) {
                        if (user.getEmail() == username.text.toString() && user.getPassword() == password.text.toString()) {
                            val prefEditor = sharedPrefs.edit()
                            prefEditor.putString(USERNAME, user.getName())
                            prefEditor.putString(PASSWORD, password.text.toString())
                            prefEditor.apply()

                            val intent = Intent(this@MainActivity, MenuDrivenActivity::class.java)
                            intent.putExtra(USERNAME, user.getName())
                            intent.putExtra(USER_BOOL, false)
                            startActivity(intent)
                        } else {
                            incorrectCredentials.visibility = View.VISIBLE
                            incorrectCredentials.text = getString(R.string.invalid_credentials)
                        }
                    }
                } else
                {
                    Toast.makeText(this@MainActivity, "No record found. Please register first.", Toast.LENGTH_SHORT).show()
                }
            } else {
                incorrectCredentials.visibility = View.VISIBLE
            }
        }

        register.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            intent.putExtra(USERNAME, username.text.toString())
            startActivity(intent)
        }
    }
}
