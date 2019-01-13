package com.worldpay.techtest.app.authorize

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.worldpay.techtest.R
import com.worldpay.techtest.app.MviFragment

import com.worldpay.techtest.app.ViewModelFactory
import com.worldpay.techtest.app.authorize.model.ItemDetails
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import javax.inject.Inject

class AuthorizeFragment
    : MviFragment<AuthorizeIntent, AuthorizeRenderAction, AuthorizeViewState, AuthorizeViewLayout>(), AuthorizeViewLayout {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var render: AuthorizeViewRenderer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.authorize_fragment, container, false)
        return view
    }

    override fun inject() {
        AndroidSupportInjection.inject(this)
    }

    override fun intents(): Observable<AuthorizeIntent> = Observable.just(
        AuthorizeIntent.Init(ItemDetails(
            "", "", "", ItemDetails.Price("", 0.0)))
    )

    override fun layout(): AuthorizeViewLayout = this

    override fun model(): AuthorizeViewModel = getViewModel(viewModelFactory)

    override fun render(): AuthorizeViewRenderer = render

    override fun showItemDetails(itemDetails: ItemDetails) {
    }

    override fun showEnterCardDetailsForm() {
    }

    override fun showEnterAddressDetailsForm() {
    }

    override fun showConfirmPaymentForm() {
    }

    override fun showPaymentProgress() {
    }

    override fun showPaymentError() {
    }

    override fun showPaymentSuccess() {
    }

    companion object {
        fun newInstance(): AuthorizeFragment {
            return AuthorizeFragment()
        }
    }
}