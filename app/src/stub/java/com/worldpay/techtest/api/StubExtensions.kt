package com.worldpay.techtest.api

import android.content.Context
import okhttp3.Headers
import okhttp3.Request
import okhttp3.Response

fun Response.Builder.create(request: Request, code: Int, body: String): Response {
    this.request(request)
    this.protocol(okhttp3.Protocol.HTTP_2)
    this.message("stub")
    this.code(code)
    this.body(okhttp3.ResponseBody.create(null, body))
    this.headers(Headers.Builder().build())
    return this.build()
}

fun readFile(fileName: String, context: Context): String {
    return context.assets.open(fileName).bufferedReader().use {
        it.readText()
    }
}