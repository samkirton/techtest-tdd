package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxViewRenderer
import javax.inject.Inject

class AuthorizeViewRenderer @Inject internal constructor() : MxViewRenderer<AuthorizeViewLayout, AuthorizeViewState> {
    override fun layout(layout: AuthorizeViewLayout, state: AuthorizeViewState): Unit = when (state.view) {
        AuthorizeViewState.View.Idle -> {
        }
        is AuthorizeViewState.View.ShowItemDetails -> {
        }
        AuthorizeViewState.View.ShowCardDetailsForm -> {
        }
        AuthorizeViewState.View.ShowAddressDetailsForm -> {
        }
        AuthorizeViewState.View.ShowConfirmPayment -> {
        }
        AuthorizeViewState.View.OnPaymentInProgress -> {
        }
        AuthorizeViewState.View.OnPaymentError -> {
        }
        AuthorizeViewState.View.OnPaymentSuccess -> {
        }
    }
}