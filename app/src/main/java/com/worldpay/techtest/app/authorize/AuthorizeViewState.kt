package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxViewState
import com.worldpay.techtest.app.authorize.model.AddressDetails
import com.worldpay.techtest.app.authorize.model.CardDetails
import com.worldpay.techtest.app.authorize.model.ItemDetails

data class AuthorizeViewState(
    val view: View,
    val cardDetails: CardDetails? = null,
    val addressDetails: AddressDetails? = null
) : MxViewState {

    sealed class View {
        object Idle : View()
        data class ShowItemDetails(val itemDetails: ItemDetails) : View()
        object ShowCardDetailsForm : View()
        object ShowAddressDetailsForm : View()
        object ShowConfirmPayment : View()
        object OnPaymentInProgress : View()
        object OnPaymentError : View()
        object OnPaymentSuccess : View()
    }
}