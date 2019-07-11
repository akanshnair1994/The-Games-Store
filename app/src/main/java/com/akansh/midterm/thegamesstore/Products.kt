package com.akansh.midterm.thegamesstore

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Products : RealmObject() {
    @Required
    @PrimaryKey
    private var productId: String = ""
    @Required
    private var productName: String = ""
    private var productPrice: Double = 0.0
    private var discounts: Double = 0.0
    @Required
    private var productType: String = ""
}