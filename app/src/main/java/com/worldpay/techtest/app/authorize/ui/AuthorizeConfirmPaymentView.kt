package com.worldpay.techtest.app.authorize.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.jakewharton.rxbinding2.view.clicks
import com.worldpay.techtest.R
import com.worldpay.techtest.app.authorize.model.ItemDetails
import com.worldpay.techtest.uikit.invisible
import com.worldpay.techtest.uikit.visible
import io.reactivex.Observable
import kotlinx.android.synthetic.main.authorize_confirm_payment_view.view.*

class AuthorizeConfirmPaymentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.authorize_confirm_payment_view,this)
    }

    fun populate(itemDetails: ItemDetails) {
        authorize_confirm_payment_name_label.text = itemDetails.name
        authorize_confirm_payment_order_total_value.text = context.getString(
            R.string.authorize_item_details_price, itemDetails.price.symbol, itemDetails.price.amount.toString())
    }

    fun ctaClick(): Observable<Unit> = authorize_confirm_payment_cta.clicks()

    fun showProgress() {
        authorize_confirm_payment_progressBar.visible()
        authorize_confirm_payment_cta.invisible()
    }

    fun hideProgress() {
        authorize_confirm_payment_progressBar.invisible()
        authorize_confirm_payment_cta.visible()
    }
}