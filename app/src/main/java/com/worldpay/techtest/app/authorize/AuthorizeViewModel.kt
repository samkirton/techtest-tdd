package com.worldpay.techtest.app.authorize

import android.app.Application
import com.memtrip.mxandroid.MxViewModel
import com.worldpay.techtest.api.payment.PaymentApi
import com.worldpay.techtest.util.RxScheduler
import io.reactivex.Observable
import javax.inject.Inject

class AuthorizeViewModel @Inject internal constructor(
    private val paymentApi: PaymentApi,
    private val rx: RxScheduler,
    application: Application
) : MxViewModel<AuthorizeIntent, AuthorizeRenderAction, AuthorizeViewState>(
    AuthorizeViewState(view = AuthorizeViewState.View.Idle),
    application
) {

    override fun dispatcher(intent: AuthorizeIntent): Observable<AuthorizeRenderAction> = when (intent) {
        AuthorizeIntent.Idle -> Observable.just(AuthorizeRenderAction.Idle)
        is AuthorizeIntent.Init -> Observable.just(AuthorizeRenderAction.Idle)
        AuthorizeIntent.SelectBuyNow -> Observable.just(AuthorizeRenderAction.Idle)
        is AuthorizeIntent.EnterCardDetails -> Observable.just(AuthorizeRenderAction.Idle)
        AuthorizeIntent.SelectCardDetailsCta -> Observable.just(AuthorizeRenderAction.Idle)
        is AuthorizeIntent.EnterAddressDetails -> Observable.just(AuthorizeRenderAction.Idle)
        AuthorizeIntent.SelectAddressDetailsCta -> Observable.just(AuthorizeRenderAction.Idle)
        AuthorizeIntent.SelectPayNow -> Observable.just(AuthorizeRenderAction.Idle)
    }

    override fun reducer(previousState: AuthorizeViewState, renderAction: AuthorizeRenderAction): AuthorizeViewState = when (renderAction) {
        AuthorizeRenderAction.Idle -> previousState.copy(view = AuthorizeViewState.View.Idle)
        is AuthorizeRenderAction.ShowItemDetails -> previousState.copy(view = AuthorizeViewState.View.Idle)
        AuthorizeRenderAction.ShowCardDetailsForm -> previousState.copy(view = AuthorizeViewState.View.Idle)
        AuthorizeRenderAction.ShowAddressDetailsForm -> previousState.copy(view = AuthorizeViewState.View.Idle)
        AuthorizeRenderAction.ShowConfirmPayment -> previousState.copy(view = AuthorizeViewState.View.Idle)
        AuthorizeRenderAction.OnPaymentInProgress -> previousState.copy(view = AuthorizeViewState.View.Idle)
        AuthorizeRenderAction.OnPaymentError -> previousState.copy(view = AuthorizeViewState.View.Idle)
        AuthorizeRenderAction.OnPaymentSuccess -> previousState.copy(view = AuthorizeViewState.View.Idle)
    }
}