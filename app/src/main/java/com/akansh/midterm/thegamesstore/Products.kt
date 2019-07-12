package com.akansh.midterm.thegamesstore

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Products : RealmObject() {
    @PrimaryKey
    private var productId: Int = 0
    @Required
    private var productName: String = ""
    private var productImagePath: String = ""
    private var productPrice: Double = 0.0
    private var discounts: Double = 0.0
    @Required
    private var productType: String = ""
    private var sellerName: String = ""
    private var sellerLocation: String = ""

    fun setProductName(productName: String) {
        this.productName = productName
    }

    fun getProductName(): String {
        return productName
    }

    fun setProductImagePath(productImagePath: String) {
        this.productImagePath = productImagePath
    }

    fun getProductImagePath(): String {
        return productImagePath
    }

    fun setProductPrice(productPrice: Double) {
        this.productPrice = productPrice
    }

    fun getProductPrice(): Double {
        return productPrice
    }

    fun setProductDiscounts(productDiscounts: Double) {
        this.discounts = productDiscounts
    }

    fun getProductDiscounts(): Double {
        return discounts
    }

    fun setProductType(productType: String) {
        this.productType = productType
    }

    fun getProductType(): String {
        return productType
    }

    fun setSellerName(sellerName: String) {
        this.sellerName = sellerName
    }

    fun getSellerName(): String {
        return sellerName
    }

    fun setSellerLocation(sellerLocation: String) {
        this.sellerLocation = sellerLocation
    }

    fun getSellerLocation(): String {
        return sellerLocation
    }
}