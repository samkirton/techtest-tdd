package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxRenderAction
import com.worldpay.techtest.app.authorize.model.AddressDetails
import com.worldpay.techtest.app.authorize.model.CardDetails
import com.worldpay.techtest.app.authorize.model.ItemDetails

sealed class AuthorizeRenderAction : MxRenderAction {
    object Idle : AuthorizeRenderAction()
    data class ShowItemDetails(val itemDetails: ItemDetails) : AuthorizeRenderAction()
    data class ShowCardDetailsForm(val cardDetails: CardDetails? = null) : AuthorizeRenderAction()
    data class ShowAddressDetailsForm(val addressDetails: AddressDetails? = null) : AuthorizeRenderAction()
    object ShowConfirmPayment : AuthorizeRenderAction()
    object OnPaymentInProgress : AuthorizeRenderAction()
    object OnPaymentError : AuthorizeRenderAction()
    object OnPaymentSuccess : AuthorizeRenderAction()
}