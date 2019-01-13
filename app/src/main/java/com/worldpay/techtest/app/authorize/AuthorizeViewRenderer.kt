package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxViewRenderer
import javax.inject.Inject

class AuthorizeViewRenderer @Inject internal constructor() : MxViewRenderer<AuthorizeViewLayout, AuthorizeViewState> {
    override fun layout(layout: AuthorizeViewLayout, state: AuthorizeViewState): Unit = when (state.view) {
        AuthorizeViewState.View.Idle -> {
        }
        is AuthorizeViewState.View.ShowItemDetails -> {
            layout.showItemDetails(state.itemDetails!!)
        }
        AuthorizeViewState.View.ShowCardDetailsForm -> {
            layout.showEnterCardDetailsForm()
        }
        AuthorizeViewState.View.ShowAddressDetailsForm -> {
            layout.showEnterAddressDetailsForm()
        }
        is AuthorizeViewState.View.ShowConfirmPayment -> {
            layout.showConfirmPaymentForm(state.itemDetails!!)
        }
        AuthorizeViewState.View.OnPaymentInProgress -> {
            layout.showPaymentProgress()
        }
        AuthorizeViewState.View.OnPaymentError -> {
            layout.showPaymentError()
        }
        AuthorizeViewState.View.OnPaymentSuccess -> {
            layout.showPaymentSuccess()
        }
    }
}