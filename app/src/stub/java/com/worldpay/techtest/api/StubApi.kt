package com.worldpay.techtest.api

import android.content.Context
import com.worldpay.techtest.api.payment.authorize.AuthorizeHandler
import com.worldpay.techtest.api.payment.cancel.CancelHandler
import okhttp3.Request
import okhttp3.Response

class StubApi(
    private val context: Context,
    private val authorizeHandler: AuthorizeHandler = AuthorizeHandler(context),
    private val cancelHandler: CancelHandler = CancelHandler(context)
) {

    fun handle(
        stubScenario: StubScenario,
        request: Request
    ): Response {
        return when {
            request.url().toString().endsWith("payments") ->
                authorizeHandler.handle(stubScenario.authorize, request)
            request.url().toString().contains("cancellations") ->
                cancelHandler.handle(stubScenario.cancel, request)
            else ->
                throw IllegalStateException("url not matched")
        }
    }
}