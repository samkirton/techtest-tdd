package com.worldpay.techtest.app.authorize.model

data class AddressDetails(
    val address1: String,
    val address2: String,
    val countryCode: String,
    val postalCode: String,
    val state: String
)