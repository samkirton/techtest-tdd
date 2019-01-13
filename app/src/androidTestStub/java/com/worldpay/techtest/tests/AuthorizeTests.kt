package com.worldpay.techtest.tests

import androidx.test.espresso.Espresso.pressBack
import androidx.test.runner.AndroidJUnit4
import com.worldpay.techtest.StubActivityTestRule
import com.worldpay.techtest.api.StubScenario
import com.worldpay.techtest.api.payment.authorize.AuthorizeHandler
import com.worldpay.techtest.tests.robot.AddressDetailsRobot
import com.worldpay.techtest.tests.robot.CardDetailsRobot
import com.worldpay.techtest.tests.robot.ConfirmPaymentRobot
import com.worldpay.techtest.tests.robot.ItemDetailsRobot
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthorizeTests {

    @get:Rule
    var rule = StubActivityTestRule()

    private val itemDetailsRobot = ItemDetailsRobot()
    private val cardDetailsRobot = CardDetailsRobot()
    private val addressDetailsRobot = AddressDetailsRobot()
    private val confirmPaymentRobot = ConfirmPaymentRobot()

    @Test
    fun authorizePaymentSuccess() {
        rule.launch(StubScenario())

        itemDetailsRobot
            .verifyItemDetailsView()
            .pressCta()

        cardDetailsRobot
            .enterCardNumber("1938291823912831")
            .enterCardHolderName("MR SJ KIRTON")
            .enterExpiryDate("0122")
            .enterCvc("041")
            .pressCta()

        addressDetailsRobot
            .enterAddress1("94 Redwood Road")
            .enterAddress2("94 Redwood Road")
            .enterCounty("Hertfordshire")
            .enterPostCode("AP18CB")
            .enterCountryCode("GBP")
            .pressCta()

        confirmPaymentRobot
            .verifyConfirmPaymentView()
            .pressCta()
            .verifyPaymentSuccess()
    }

    @Test
    fun authorizePaymentErrorOnFirsAttemptButSuccessOnSecondAttempt() {
        rule.launch(StubScenario(authorize = AuthorizeHandler.Journey.GenericError))

        itemDetailsRobot
            .verifyItemDetailsView()
            .pressCta()

        cardDetailsRobot
            .enterCardNumber("1938291823912831")
            .enterCardHolderName("MR SJ KIRTON")
            .enterExpiryDate("0122")
            .enterCvc("041")
            .pressCta()

        addressDetailsRobot
            .enterAddress1("94 Redwood Road")
            .enterAddress2("94 Redwood Road")
            .enterCounty("Hertfordshire")
            .enterPostCode("AP18CB")
            .enterCountryCode("GBP")
            .pressCta()

        confirmPaymentRobot
            .verifyConfirmPaymentView()
            .pressCta()
            .verifyPaymentFailed()
            .dismissDialog()
            .pressCta()
            .verifyPaymentSuccess()
    }

    @Test
    fun authorizePaymentBackStack() {
        rule.launch(StubScenario(authorize = AuthorizeHandler.Journey.GenericError))

        itemDetailsRobot
            .verifyItemDetailsView()
            .pressCta()

        cardDetailsRobot
            .enterCardNumber("1938291823912831")
            .enterCardHolderName("MR SJ KIRTON")
            .enterExpiryDate("0122")
            .enterCvc("041")
            .pressCta()

        addressDetailsRobot
            .enterAddress1("94 Redwood Road")
            .enterAddress2("94 Redwood Road")
            .enterCounty("Hertfordshire")
            .enterPostCode("AP18CB")
            .enterCountryCode("GBP")
            .pressCta()

        confirmPaymentRobot
            .verifyConfirmPaymentView()

        pressBack()

        addressDetailsRobot
            .verifyAddressDetailsView()

        pressBack()

        cardDetailsRobot
            .verifyCardDetailsView()

        pressBack()

        itemDetailsRobot
            .verifyItemDetailsView()
    }
}
