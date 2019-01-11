package com.worldpay.techtest.authorize

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.worldpay.techtest.app.authorize.AuthorizeViewLayout
import com.worldpay.techtest.app.authorize.AuthorizeViewRenderer
import com.worldpay.techtest.app.authorize.AuthorizeViewState
import com.worldpay.techtest.app.authorize.model.ItemDetails
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class AuthorizeViewRendererTests : Spek({

    given("AuthorizeViewRenderer") {

        on("View.ShowItemDetails") {
            val layout: AuthorizeViewLayout = mock()
            val renderer = AuthorizeViewRenderer()

            val itemDetails = mock<ItemDetails>()

            renderer.layout(layout, AuthorizeViewState(view = AuthorizeViewState.View.ShowItemDetails(itemDetails)))

            it("layout.showPaymentProgress") {
                verify(layout).showItemDetails(itemDetails)
            }
        }

        on("View.ShowCardDetailsForm") {
            val layout: AuthorizeViewLayout = mock()
            val renderer = AuthorizeViewRenderer()

            renderer.layout(layout, AuthorizeViewState(view = AuthorizeViewState.View.ShowCardDetailsForm))

            it("layout.showEnterCardDetailsForm") {
                verify(layout).showEnterCardDetailsForm()
            }
        }

        on("View.ShowAddressDetailsForm") {
            val layout: AuthorizeViewLayout = mock()
            val renderer = AuthorizeViewRenderer()

            renderer.layout(layout, AuthorizeViewState(view = AuthorizeViewState.View.ShowAddressDetailsForm))

            it("layout.showEnterAddressDetailsForm") {
                verify(layout).showEnterAddressDetailsForm()
            }
        }

        on("View.ShowConfirmPayment") {
            val layout: AuthorizeViewLayout = mock()
            val renderer = AuthorizeViewRenderer()

            renderer.layout(layout, AuthorizeViewState(view = AuthorizeViewState.View.ShowConfirmPayment))

            it("layout.showConfirmPaymentForm") {
                verify(layout).showConfirmPaymentForm()
            }
        }

        on("View.OnPaymentInProgress") {
            val layout: AuthorizeViewLayout = mock()
            val renderer = AuthorizeViewRenderer()

            renderer.layout(layout, AuthorizeViewState(view = AuthorizeViewState.View.OnPaymentInProgress))

            it("layout.showPaymentProgress") {
                verify(layout).showPaymentProgress()
            }
        }

        on("View.OnPaymentError") {
            val layout: AuthorizeViewLayout = mock()
            val renderer = AuthorizeViewRenderer()

            renderer.layout(layout, AuthorizeViewState(view = AuthorizeViewState.View.OnPaymentError))

            it("layout.showPaymentError") {
                verify(layout).showPaymentError()
            }
        }

        on("View.OnPaymentSuccess") {
            val layout: AuthorizeViewLayout = mock()
            val renderer = AuthorizeViewRenderer()

            renderer.layout(layout, AuthorizeViewState(view = AuthorizeViewState.View.OnPaymentSuccess))

            it("layout.showPaymentSuccess") {
                verify(layout).showPaymentSuccess()
            }
        }
    }
})