package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxRenderAction

sealed class AuthorizeRenderAction : MxRenderAction {
    object Idle : AuthorizeRenderAction()
    object OnProgress : AuthorizeRenderAction()
    object OnError : AuthorizeRenderAction()
}