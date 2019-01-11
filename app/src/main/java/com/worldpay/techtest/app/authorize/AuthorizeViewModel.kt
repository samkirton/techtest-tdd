package com.worldpay.techtest.app.authorize

import android.app.Application
import com.worldpay.techtest.api.payment.PaymentApi
import com.worldpay.techtest.util.RxScheduler
import com.memtrip.mxandroid.MxViewModel
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
        AuthorizeIntent.Retry -> Observable.just(AuthorizeRenderAction.Idle)
    }

    override fun reducer(previousState: AuthorizeViewState, renderAction: AuthorizeRenderAction): AuthorizeViewState = when (renderAction) {
        AuthorizeRenderAction.Idle -> previousState.copy(view = AuthorizeViewState.View.Idle)
        AuthorizeRenderAction.OnProgress -> previousState.copy(view = AuthorizeViewState.View.OnProgress)
        AuthorizeRenderAction.OnError -> previousState.copy(view = AuthorizeViewState.View.OnError)
    }
}