package com.worldpay.techtest.tests.robot

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.worldpay.techtest.R

class ConfirmPaymentRobot {

    fun verifyConfirmPaymentView() = apply {

        onView(withId(R.id.authorize_confirm_payment_name_label))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_confirm_payment_order_total_label))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_confirm_payment_order_total_value))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_confirm_payment_cta))
            .check(matches(isDisplayed()))
    }

    fun pressCta() = apply {

        onView(withId(R.id.authorize_confirm_payment_cta))
            .check(matches(isDisplayed()))
            .perform(click())
    }

    fun verifyPaymentSuccess() = apply {

        onView(withText(R.string.authorize_confirm_payment_dialog_body_success))
            .check(matches(isDisplayed()))
    }

    fun verifyPaymentFailed() = apply {

        onView(withText(R.string.authorize_confirm_payment_dialog_body_failed))
            .check(matches(isDisplayed()))
    }

    fun dismissDialog() = apply {
        pressBack()
    }
}