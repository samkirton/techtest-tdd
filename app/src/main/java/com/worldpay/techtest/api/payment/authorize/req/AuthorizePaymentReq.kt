package com.worldpay.techtest.api.payment.authorize.req

data class AuthorizePaymentReq(
    val transactionReference: String,
    val instruction: InstructionReq
)