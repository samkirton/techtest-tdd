@file:Suppress("LocalVariableName", "LocalVariableName")

package com.worldpay.techtest.authorize

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.worldpay.techtest.api.payment.PaymentApi
import com.worldpay.techtest.api.payment.authorize.req.AuthorizePaymentReq
import com.worldpay.techtest.api.payment.authorize.res.AuthorizePaymentRes
import com.worldpay.techtest.app.authorize.AuthorizeIntent
import com.worldpay.techtest.app.authorize.AuthorizeViewModel
import com.worldpay.techtest.app.authorize.AuthorizeViewState
import com.worldpay.techtest.app.authorize.model.AddressDetails
import com.worldpay.techtest.app.authorize.model.CardDetails
import com.worldpay.techtest.app.authorize.model.ItemDetails
import com.worldpay.techtest.util.TestRxScheduler
import com.worldpay.techtest.util.first
import com.worldpay.techtest.util.get
import io.reactivex.Observable
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import retrofit2.Response

@RunWith(JUnitPlatform::class)
class AuthorizeViewModelTests : Spek({

    given("AuthorizeViewModel") {

        val api by memoized { mock<PaymentApi>() }
        val testRxScheduler by memoized { TestRxScheduler() }
        val viewModel by memoized { AuthorizeViewModel(api, testRxScheduler, mock()) }

        on("Init") {

            val itemDetails = mock<ItemDetails>()
            viewModel.processIntents(Observable.just(AuthorizeIntent.Init(itemDetails)))

            val states = viewModel.states().test()

            it ("should show the item details") {
                assertEquals(
                    AuthorizeViewState.View.ShowItemDetails(itemDetails),
                    states.first().view
                )
            }
        }

        on("SelectBuyNow") {

            viewModel.processIntents(Observable.just(AuthorizeIntent.SelectBuyNow))

            val states = viewModel.states().test()

            it ("should show the card details form") {
                assertEquals(
                    AuthorizeViewState.View.ShowCardDetailsForm,
                    states.first().view
                )
            }
        }

        on("EnterCardDetails") {

            val cardDetails = mock<CardDetails>()
            viewModel.processIntents(Observable.just(AuthorizeIntent.EnterCardDetails(cardDetails)))

            val states = viewModel.states().test()

            it ("should show the card details form, with the cards details model updated") {
                assertEquals(AuthorizeViewState.View.ShowCardDetailsForm,
                    states.first().view)
                assertEquals(cardDetails, states.first().cardDetails)
            }
        }

        on("SelectCardDetailsCta") {

            viewModel.processIntents(Observable.just(AuthorizeIntent.SelectCardDetailsCta))

            val states = viewModel.states().test()

            it ("should show the address details form") {
                assertEquals(
                    AuthorizeViewState.View.ShowAddressDetailsForm,
                    states.first().view
                )
            }
        }

        on("EnterAddressDetails") {

            val addressDetails = mock<AddressDetails>()
            viewModel.processIntents(Observable.just(AuthorizeIntent.EnterAddressDetails(addressDetails)))

            val states = viewModel.states().test()

            it ("should show the address details form, with the address details model updated") {
                assertEquals(
                    AuthorizeViewState.View.ShowAddressDetailsForm,
                    states.first().view
                )
                assertEquals(addressDetails,
                    states.first().addressDetails)
            }
        }

        on("SelectAddressDetailsCta") {

            viewModel.processIntents(Observable.just(AuthorizeIntent.SelectAddressDetailsCta))

            val states = viewModel.states().test()

            it ("should show confirm payment") {
                assertEquals(AuthorizeViewState.View.ShowConfirmPayment,
                    states.first().view)
            }
        }

        on("SelectPayNow, and the payment is successful") {

            // given
            val req = mock<AuthorizePaymentReq>()
            val res = mock<AuthorizePaymentRes>()
            val response = mock<Response<AuthorizePaymentRes>> {
                on {
                    isSuccessful
                }.thenReturn(true)

                on {
                    body()
                }.thenReturn(res)
            }

            whenever(api.authorizePayment(req)).thenReturn(Single.just(response))

            // when
            viewModel.processIntents(Observable.just(AuthorizeIntent.EnterCardDetails(CardDetails(
                "321",
                "VISA",
                "1029319293219341",
                "SJ KIRTON",
                CardDetails.Expiry(3, 2022)
            ))))

            viewModel.processIntents(Observable.just(AuthorizeIntent.EnterAddressDetails(AddressDetails(
                "46 Redwood Drive",
                "Edinburgh",
                "GBR",
                "EH18RP",
                "Lothian"))))

            viewModel.processIntents(Observable.just(AuthorizeIntent.SelectPayNow))

            val states = viewModel.states().test()

            it ("should show payment success") {
                assertEquals(AuthorizeViewState.View.ShowCardDetailsForm,
                    states.get(0).view)
                assertEquals(AuthorizeViewState.View.ShowAddressDetailsForm,
                    states.get(1).view)
                assertEquals(AuthorizeViewState.View.OnPaymentSuccess,
                    states.get(2).view)
            }
        }

        on("SelectPayNow, and the payment failed") {

            // given
            val req = mock<AuthorizePaymentReq>()
            val res = mock<AuthorizePaymentRes>()
            val response = mock<Response<AuthorizePaymentRes>> {
                on {
                    isSuccessful
                }.thenReturn(false)

                on {
                    body()
                }.thenReturn(res)
            }

            whenever(api.authorizePayment(req)).thenReturn(Single.just(response))

            // when
            viewModel.processIntents(Observable.just(AuthorizeIntent.EnterCardDetails(CardDetails(
                "321",
                "VISA",
                "1029319293219341",
                "SJ KIRTON",
                CardDetails.Expiry(3, 2022)
            ))))

            viewModel.processIntents(Observable.just(AuthorizeIntent.EnterAddressDetails(AddressDetails(
                "46 Redwood Drive",
                "Edinburgh",
                "GBR",
                "EH18RP",
                "Lothian"))))

            viewModel.processIntents(Observable.just(AuthorizeIntent.SelectPayNow))

            val states = viewModel.states().test()

            it ("should show payment success") {
                assertEquals(AuthorizeViewState.View.ShowCardDetailsForm,
                    states.get(0).view)
                assertEquals(AuthorizeViewState.View.ShowAddressDetailsForm,
                    states.get(1).view)
                assertEquals(AuthorizeViewState.View.OnPaymentError,
                    states.get(2).view)
            }
        }
    }
})