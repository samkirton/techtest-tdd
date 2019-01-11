package com.worldpay.techtest.api.payment.authorize.req

data class InstructionReq(
    val description: String,
    val value: Value,
    val paymentInstrument: PaymentInstrumentReq
) {
    data class Value(
        val currency: String,
        val amount: String
    )
}