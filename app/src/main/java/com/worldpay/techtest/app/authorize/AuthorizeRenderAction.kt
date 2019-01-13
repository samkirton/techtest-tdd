package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxRenderAction
import com.worldpay.techtest.app.authorize.model.ItemDetails

sealed class AuthorizeRenderAction : MxRenderAction {
    object Idle : AuthorizeRenderAction()
    data class ShowItemDetails(val itemDetails: ItemDetails) : AuthorizeRenderAction()
    object ShowCardDetailsForm : AuthorizeRenderAction()
    object ShowAddressDetailsForm : AuthorizeRenderAction()
    object ShowConfirmPayment : AuthorizeRenderAction()
    object OnPaymentInProgress : AuthorizeRenderAction()
    object OnPaymentError : AuthorizeRenderAction()
    object OnPaymentSuccess : AuthorizeRenderAction()
}