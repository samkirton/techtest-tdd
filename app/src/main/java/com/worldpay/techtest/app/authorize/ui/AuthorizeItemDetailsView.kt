package com.worldpay.techtest.app.authorize.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.clicks
import com.worldpay.techtest.R
import com.worldpay.techtest.app.authorize.model.ItemDetails
import io.reactivex.Observable
import kotlinx.android.synthetic.main.authorize_item_details_view.view.*

class AuthorizeItemDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.authorize_item_details_view,this)
    }

    fun populate(itemDetails: ItemDetails) {
        authorize_item_details_name_label.text = itemDetails.name
        authorize_item_details_price_label.text = context.getString(
            R.string.authorize_item_details_price, itemDetails.price.symbol, itemDetails.price.amount.toString())
        authorize_item_details_description_label.text = itemDetails.description
    }

    fun ctaClick(): Observable<Unit> = authorize_item_details_cta.clicks()
}