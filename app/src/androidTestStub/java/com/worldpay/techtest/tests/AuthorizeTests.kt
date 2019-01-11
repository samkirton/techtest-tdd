package com.worldpay.techtest.tests

import androidx.test.runner.AndroidJUnit4
import com.worldpay.techtest.StubActivityTestRule
import com.worldpay.techtest.api.StubScenario
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthorizeTests {

    @get:Rule
    var rule = StubActivityTestRule()

    @Test
    fun paymentSuccess() {
        rule.launch(StubScenario())
    }

    @Test
    fun paymentErrorSuccessAfterRetry() {
        rule.launch(StubScenario())
    }

    @Test
    fun cancelPaymentSuccess() {
        rule.launch(StubScenario())
    }

    @Test
    fun cancelErrorSuccessAfterRetry() {
        rule.launch(StubScenario())
    }
}
