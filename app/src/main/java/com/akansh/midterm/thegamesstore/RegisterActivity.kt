package com.akansh.midterm.thegamesstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

class RegisterActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var username: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var password: EditText
    private lateinit var retypePassword: EditText
    private lateinit var register: AppCompatButton
    private lateinit var login: TextView
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        realm = Realm.getDefaultInstance()
        name = findViewById(R.id.name)
        username = findViewById(R.id.username)
        phoneNumber = findViewById(R.id.phoneNumber)
        password = findViewById(R.id.password)
        retypePassword = findViewById(R.id.retypePassword)
        register = findViewById(R.id.register)
        login = findViewById(R.id.login)


        register.setOnClickListener {
            val results: RealmResults<Users> = realm.where(Users::class.java).findAll()
            if (results.isEmpty()) {
                addUserToSystem(1, name.text.toString(), username.text.toString(), phoneNumber.text.toString().toLong(), password.text.toString())
                intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)

                finish()
            } else {
                for (user: Users in results) {
                    if (user.getEmail() == username.text.toString()) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "The user already exists. Please login.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        addUserToSystem((results.size + 1), name.text.toString(), username.text.toString(), phoneNumber.text.toString().toLong(), password.text.toString())
                        intent = Intent(this@RegisterActivity, MainActivity::class.java)
                        startActivity(intent)

                        finish()
                    }
                }
            }
        }

        login.setOnClickListener {
            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
            startActivity(intent)

            finish()
        }
    }

    private fun addUserToSystem(id: Int, name: String, username: String, phoneNumber: Long, password: String) {
        realm.beginTransaction()

        val newUser = realm.createObject(Users::class.java, 1)
        newUser.setName(name)
        newUser.setEmail(username)
        newUser.setPhone(phoneNumber)
        newUser.setPassword(password)
        realm.commitTransaction()
    }
}
