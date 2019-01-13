package com.worldpay.techtest.app.authorize

import android.app.Application
import com.memtrip.mxandroid.MxViewModel
import com.worldpay.techtest.api.payment.PaymentApi
import com.worldpay.techtest.api.payment.authorize.req.AuthorizePaymentReq
import com.worldpay.techtest.api.payment.authorize.req.InstructionReq
import com.worldpay.techtest.api.payment.authorize.req.PaymentInstrumentReq
import com.worldpay.techtest.app.authorize.model.AddressDetails
import com.worldpay.techtest.app.authorize.model.CardDetails
import com.worldpay.techtest.app.authorize.model.ItemDetails
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
        AuthorizeIntent.Idle ->
            Observable.just(AuthorizeRenderAction.Idle)
        is AuthorizeIntent.Init ->
            Observable.just(AuthorizeRenderAction.ShowItemDetails(intent.itemDetails))
        AuthorizeIntent.SelectBuyNow ->
            Observable.just(AuthorizeRenderAction.ShowCardDetailsForm())
        is AuthorizeIntent.EnterCardDetails ->
            Observable.just(AuthorizeRenderAction.ShowCardDetailsForm(intent.cardDetails))
        AuthorizeIntent.SelectCardDetailsCta ->
            Observable.just(AuthorizeRenderAction.ShowAddressDetailsForm())
        is AuthorizeIntent.EnterAddressDetails ->
            Observable.just(AuthorizeRenderAction.ShowAddressDetailsForm(intent.addressDetails))
        AuthorizeIntent.SelectAddressDetailsCta ->
            Observable.just(AuthorizeRenderAction.ShowConfirmPayment)
        is AuthorizeIntent.SelectPayNow ->
            authorizePayment(intent.itemDetails, intent.cardDetails, intent.addressDetails)
    }

    override fun reducer(previousState: AuthorizeViewState, renderAction: AuthorizeRenderAction) = when (renderAction) {
        AuthorizeRenderAction.Idle -> previousState.copy(
            view = AuthorizeViewState.View.Idle)
        is AuthorizeRenderAction.ShowItemDetails -> previousState.copy(
            view = AuthorizeViewState.View.ShowItemDetails(renderAction.itemDetails),
            itemDetails = renderAction.itemDetails)
        is AuthorizeRenderAction.ShowCardDetailsForm -> previousState.copy(
            view = AuthorizeViewState.View.ShowCardDetailsForm,
            cardDetails = renderAction.cardDetails)
        is AuthorizeRenderAction.ShowAddressDetailsForm -> previousState.copy(
            view = AuthorizeViewState.View.ShowAddressDetailsForm,
            addressDetails = renderAction.addressDetails)
        AuthorizeRenderAction.ShowConfirmPayment -> previousState.copy(
            view = AuthorizeViewState.View.ShowConfirmPayment)
        AuthorizeRenderAction.OnPaymentInProgress -> previousState.copy(
            view = AuthorizeViewState.View.OnPaymentInProgress)
        AuthorizeRenderAction.OnPaymentError -> previousState.copy(
            view = AuthorizeViewState.View.OnPaymentError)
        AuthorizeRenderAction.OnPaymentSuccess -> previousState.copy(
            view = AuthorizeViewState.View.OnPaymentSuccess)
    }

    private fun authorizePayment(
        itemDetails: ItemDetails,
        cardDetails: CardDetails,
        addressDetails: AddressDetails
    ): Observable<AuthorizeRenderAction> {
        return paymentApi.authorizePayment(createAuthorizePaymentReq(
            itemDetails,
            cardDetails,
            addressDetails
        )).observeOn(rx.main()).subscribeOn(rx.thread()).map { response ->
            if (response.isSuccessful) {
                AuthorizeRenderAction.OnPaymentSuccess
            } else {
                AuthorizeRenderAction.OnPaymentError
            }
        }.onErrorReturn {
            AuthorizeRenderAction.OnPaymentError
        }.toObservable().startWith(AuthorizeRenderAction.OnPaymentInProgress)
    }

    companion object {
        fun createAuthorizePaymentReq(
            itemDetails: ItemDetails,
            cardDetails: CardDetails,
            addressDetails: AddressDetails
        ): AuthorizePaymentReq {
            return AuthorizePaymentReq(
                itemDetails.ref,
                InstructionReq(
                    itemDetails.description,
                    InstructionReq.Value(
                        itemDetails.price.symbol,
                        itemDetails.price.amount.toString()),
                    PaymentInstrumentReq(
                        cardDetails.cvc,
                        resolveCardType(cardDetails.cardNumber),
                        cardDetails.cardNumber,
                        cardDetails.cardHolderName,
                        PaymentInstrumentReq.Address(
                            addressDetails.address1,
                            addressDetails.address2,
                            addressDetails.countryCode,
                            addressDetails.postalCode,
                            addressDetails.state),
                        PaymentInstrumentReq.CardExpiry(
                            cardDetails.cardExpiryDate.month,
                            cardDetails.cardExpiryDate.year)
                    )
                )
            )
        }

        private fun resolveCardType(cardNumber: String): String {
            return "VISA"
        }
    }
}