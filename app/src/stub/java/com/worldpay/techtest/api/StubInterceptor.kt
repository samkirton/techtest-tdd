package com.worldpay.techtest.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class StubInterceptor(
    private val stubScenario: StubScenario,
    context: Context,
    private val stubApi: StubApi = StubApi(context)
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return stubApi.handle(stubScenario, chain.request())
    }
}