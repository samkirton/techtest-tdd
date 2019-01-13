package com.worldpay.techtest.app.authorize.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.widget.textChanges
import com.worldpay.techtest.R
import com.worldpay.techtest.app.authorize.model.AddressDetails
import com.worldpay.techtest.uikit.isNotEmpty
import com.worldpay.techtest.uikit.string
import io.reactivex.Observable
import kotlinx.android.synthetic.main.authorize_address_details_form_view.view.*

class AuthorizeAddressDetailsFormView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.authorize_address_details_form_view,this)
    }

    fun formChanged(): Observable<AddressDetails> = Observable.mergeArray(
        authorize_address_details_address1_editText.textChanges(),
        authorize_address_details_address2_editText.textChanges(),
        authorize_address_details_county_editText.textChanges(),
        authorize_address_details_post_code_editText.textChanges(),
        authorize_address_details_country_code_editText.textChanges()
    ).filter {
        with(authorize_address_details_address1_editText.isNotEmpty() &&
                authorize_address_details_county_editText.isNotEmpty() &&
                authorize_address_details_post_code_editText.isNotEmpty() &&
                authorize_address_details_country_code_editText.isNotEmpty()
        ) {
            authorize_address_details_cta.isEnabled = this
            this
        }
    }.map {
        authorize_address_details_cta.isEnabled = true
        addressDetails()
    }

    fun addressDetails(): AddressDetails {
        return AddressDetails(
            authorize_address_details_address1_editText.string(),
            authorize_address_details_address2_editText.string(),
            authorize_address_details_country_code_editText.string(),
            authorize_address_details_post_code_editText.string(),
            authorize_address_details_county_editText.string()
        )
    }

    fun ctaClick(): Observable<Unit> = authorize_address_details_cta.clicks()
}