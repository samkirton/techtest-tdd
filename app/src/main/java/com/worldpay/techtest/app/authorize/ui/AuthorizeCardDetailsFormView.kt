package com.worldpay.techtest.app.authorize.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import com.worldpay.techtest.R
import com.worldpay.techtest.app.authorize.model.CardDetails
import com.worldpay.techtest.uikit.isNotEmpty
import com.worldpay.techtest.uikit.range
import com.worldpay.techtest.uikit.string
import io.reactivex.Observable
import kotlinx.android.synthetic.main.authorize_card_details_form_view.view.*

class AuthorizeCardDetailsFormView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.authorize_card_details_form_view,this)
    }

    fun formChanged(): Observable<CardDetails> = Observable.mergeArray(
        authorize_card_details_card_number_editText.textChanges(),
        authorize_card_details_card_holder_name_editText.textChanges(),
        authorize_card_details_expiry_editText.textChanges(),
        authorize_card_details_cvc_editText.textChanges()
    ).filter {
        with(authorize_card_details_card_number_editText.isNotEmpty() &&
            authorize_card_details_card_holder_name_editText.isNotEmpty() &&
            authorize_card_details_expiry_editText.isNotEmpty() &&
            authorize_card_details_expiry_editText.length() == 4 &&
            authorize_card_details_cvc_editText.isNotEmpty()
        ) {
            authorize_card_details_cta.isEnabled = this
            this
        }
    }.map {
        authorize_card_details_cta.isEnabled = true
        cardDetails()
    }

    fun cardDetails(): CardDetails {
        return CardDetails(
            authorize_card_details_cvc_editText.string(),
            authorize_card_details_card_number_editText.string(),
            authorize_card_details_card_holder_name_editText.string(),
            CardDetails.Expiry(
                authorize_card_details_expiry_editText.range(0,2).toInt(),
                authorize_card_details_expiry_editText.range(1,3).toInt()))
    }

    fun ctaClick(): Observable<Unit> = authorize_card_details_cta.clicks()
}