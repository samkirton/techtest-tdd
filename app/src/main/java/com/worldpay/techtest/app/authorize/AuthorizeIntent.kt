package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxViewIntent
import com.worldpay.techtest.app.authorize.model.AddressDetails
import com.worldpay.techtest.app.authorize.model.CardDetails
import com.worldpay.techtest.app.authorize.model.ItemDetails

sealed class AuthorizeIntent : MxViewIntent {
    object Idle : AuthorizeIntent()
    data class Init(val itemDetails: ItemDetails) : AuthorizeIntent()
    object SelectBuyNow : AuthorizeIntent()
    data class EnterCardDetails(val cardDetails: CardDetails) : AuthorizeIntent()
    object SelectCardDetailsCta : AuthorizeIntent()
    data class EnterAddressDetails(val addressDetails: AddressDetails) : AuthorizeIntent()
    object SelectAddressDetailsCta : AuthorizeIntent()
    object SelectPayNow : AuthorizeIntent()
}