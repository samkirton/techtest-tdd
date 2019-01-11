package com.worldpay.techtest.api.payment

import com.worldpay.techtest.api.payment.authorize.req.AuthorizePaymentReq
import com.worldpay.techtest.api.payment.authorize.res.AuthorizePaymentRes
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface PaymentApi {

    @POST("payments")
    fun authorizePayment(@Body req: AuthorizePaymentReq): Single<Response<AuthorizePaymentRes>>

    @POST("payments/authorizations/cancellations/{id}")
    fun cancel(@Path("id") id: String)
}