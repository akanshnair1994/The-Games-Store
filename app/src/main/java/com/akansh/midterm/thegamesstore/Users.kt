package com.akansh.midterm.thegamesstore

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class Users : RealmModel {
    @PrimaryKey
    private var userId = 0
    private var name: String = ""
    private var phone: Long = 0
    @Required
    private var email: String = ""
    @Required
    private var password: String = ""
    private var admin: Boolean = false

    fun setUserId(userId: Int) {
        this.userId = userId
    }

    fun getUserId(): Int {
        return userId
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return name
    }

    fun setPhone(phone: Long) {
        this.phone = phone
    }

    fun getPhone(): Long {
        return phone
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getEmail(): String {
        return email
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getPassword(): String {
        return password
    }

    fun setAdmin(admin: Boolean) {
        this.admin = admin
    }

    fun getAdmin(): Boolean {
        return admin
    }
}