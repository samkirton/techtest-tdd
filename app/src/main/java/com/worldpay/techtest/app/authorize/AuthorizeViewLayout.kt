package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxViewLayout
import com.worldpay.techtest.app.authorize.model.ItemDetails

interface AuthorizeViewLayout : MxViewLayout {
    fun showItemDetails(itemDetails: ItemDetails)
    fun showEnterCardDetailsForm()
    fun showEnterAddressDetailsForm()
    fun showConfirmPaymentForm()
    fun showPaymentProgress()
    fun showPaymentError()
    fun showPaymentSuccess()
}