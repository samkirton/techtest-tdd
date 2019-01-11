package com.worldpay.techtest.app.authorize.model

data class CardDetails(
    val cvc: String,
    val type: String,
    val cardNumber: String,
    val cardHolderName: String,
    val cardExpiryDate: Expiry
) {
    data class Expiry(
        val month: Int,
        val year: Int
    )
}