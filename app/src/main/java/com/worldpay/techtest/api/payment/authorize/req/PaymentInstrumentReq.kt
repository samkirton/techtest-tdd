package com.worldpay.techtest.api.payment.authorize.req

data class PaymentInstrumentReq(
    val cvc: String,
    val type: String,
    val cardNumber: String,
    val cardHolderName: String,
    val billingAddress: Address,
    val cardExpiryDate: CardExpiry
) {
    data class Address(
        val address1: String,
        val address2: String,
        val countryCode: String,
        val postalCode: String,
        val state: String
    )

    data class CardExpiry(
        val month: Int,
        val year: Int
    )
}