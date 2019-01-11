package com.worldpay.techtest.api.payment.authorize

import android.content.Context
import com.worldpay.techtest.api.create
import com.worldpay.techtest.api.readFile
import okhttp3.Request
import okhttp3.Response
import java.util.*

data class AuthorizeHandler(
    private val context: Context
) {

    fun handle(
        journey: Journey,
        request: Request
    ): Response = when (journey) {
        is Journey.Success -> {
            Response.Builder().create(request, 200, readFile("stub/authorizePaymentRes.json", context))
        }
        Journey.GenericError -> {
            errorResponses.pop()
        }
    }

    private val errorResponses: LinkedList<Response> = with(LinkedList<Response>()) {
        add(
            Response.Builder().create(
                Request.Builder().url("http://stub").build(), 400, ""))
        add(
            Response.Builder().create(
                Request.Builder().url("http://stub").build(), 200, readFile("stub/authorizePaymentRes.json", context)))
        this
    }

    sealed class Journey {
        object Success : Journey()
        object GenericError : Journey()
    }
}