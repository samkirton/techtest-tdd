package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxViewState

data class AuthorizeViewState(
    val view: View
) : MxViewState {

    sealed class View {
        object Idle : View()
        object OnProgress : View()
        object OnError : View()
    }
}