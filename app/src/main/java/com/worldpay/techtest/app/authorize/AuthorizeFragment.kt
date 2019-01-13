package com.worldpay.techtest.app.authorize

import android.app.AlertDialog
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.worldpay.techtest.R
import com.worldpay.techtest.app.MviFragment
import com.worldpay.techtest.app.ViewModelFactory
import com.worldpay.techtest.app.authorize.model.ItemDetails
import com.worldpay.techtest.uikit.gone
import com.worldpay.techtest.uikit.visible
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.authorize_fragment.*
import javax.inject.Inject

class AuthorizeFragment
    : MviFragment<AuthorizeIntent, AuthorizeRenderAction, AuthorizeViewState, AuthorizeViewLayout>(), AuthorizeViewLayout {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var render: AuthorizeViewRenderer

    lateinit var itemDetails: ItemDetails

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.authorize_fragment, container, false)
        itemDetails = itemDetails(arguments!!)
        return view
    }

    override fun inject() {
        AndroidSupportInjection.inject(this)
    }

    override fun intents(): Observable<AuthorizeIntent> = Observable.mergeArray(
        Observable.just(AuthorizeIntent.Init(itemDetails)),
        authorize_item_details_view.ctaClick().map {
            AuthorizeIntent.SelectBuyNow
        },
        authorize_card_details_form_view.formChanged().map { cardDetails ->
            AuthorizeIntent.EnterCardDetails(cardDetails)
        },
        authorize_card_details_form_view.ctaClick().map {
            AuthorizeIntent.SelectCardDetailsCta
        },
        authorize_address_details_form_view.formChanged().map { cardDetails ->
            AuthorizeIntent.EnterAddressDetails(cardDetails)
        },
        authorize_address_details_form_view.ctaClick().map {
            AuthorizeIntent.SelectAddressDetailsCta
        },
        authorize_confirm_payment_view.ctaClick().map {
            AuthorizeIntent.SelectPayNow(
                itemDetails,
                authorize_card_details_form_view.cardDetails(),
                authorize_address_details_form_view.addressDetails())
        }
    )

    override fun layout(): AuthorizeViewLayout = this

    override fun model(): AuthorizeViewModel = getViewModel(viewModelFactory)

    override fun render(): AuthorizeViewRenderer = render

    override fun showItemDetails(itemDetails: ItemDetails) {
        authorize_item_details_view.populate(itemDetails)
    }

    override fun showEnterCardDetailsForm() {
        authorize_item_details_view.gone()
        authorize_card_details_form_view.visible()
    }

    override fun showEnterAddressDetailsForm() {
        authorize_card_details_form_view.gone()
        authorize_address_details_form_view.visible()
    }

    override fun showConfirmPaymentForm(itemDetails: ItemDetails) {
        hideKeyboard()
        authorize_address_details_form_view.gone()
        authorize_confirm_payment_view.visible()
        authorize_confirm_payment_view.populate(itemDetails)
    }

    override fun showPaymentProgress() {
        authorize_confirm_payment_view.showProgress()
    }

    override fun showPaymentError() {
        authorize_confirm_payment_view.hideProgress()
        AlertDialog.Builder(context!!)
            .setTitle(R.string.authorize_confirm_payment_dialog_body_failed)
            .setPositiveButton(R.string.app_dialog_ok_button, null)
            .create()
            .show()
    }

    override fun showPaymentSuccess() {
        authorize_confirm_payment_view.hideProgress()
        AlertDialog.Builder(context!!)
            .setTitle(R.string.authorize_confirm_payment_dialog_body_success)
            .setPositiveButton(R.string.app_dialog_ok_button, null)
            .create()
            .show()
    }

    @Parcelize
    data class ItemDetailsBundle(
        val name: String,
        val description: String,
        val ref: String,
        val symbol: String,
        val amount: Double
    ) : Parcelable

    companion object {
        fun newInstance(itemDetails: ItemDetails): AuthorizeFragment {
            return with (AuthorizeFragment()) {
                arguments = with(Bundle()) {
                    putParcelable("bundle", ItemDetailsBundle(
                        itemDetails.name,
                        itemDetails.description,
                        itemDetails.ref,
                        itemDetails.price.symbol,
                        itemDetails.price.amount
                    ))
                    this
                }
                this
            }
        }

        fun itemDetails(bundle: Bundle): ItemDetails {
            return with(bundle.getParcelable("bundle") as ItemDetailsBundle) {
                ItemDetails(
                    name,
                    description,
                    ref,
                    ItemDetails.Price(symbol, amount)
                )
            }
        }
    }
}