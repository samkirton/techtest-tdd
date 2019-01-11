package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxViewIntent

sealed class AuthorizeIntent : MxViewIntent {
    object Idle : AuthorizeIntent()
    object Init : AuthorizeIntent()
    object Retry : AuthorizeIntent()
}