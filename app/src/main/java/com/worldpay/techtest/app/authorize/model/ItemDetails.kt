package com.worldpay.techtest.app.authorize.model

data class ItemDetails(
    val name: String,
    val description: String,
    val ref: String,
    val price: Price
) {
    data class Price(
        val symbol: String,
        val amount: Double
    )
}