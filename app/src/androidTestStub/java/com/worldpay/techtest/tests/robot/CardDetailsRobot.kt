package com.worldpay.techtest.tests.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.worldpay.techtest.R

class CardDetailsRobot {

    fun verifyCardDetailsView() = apply {
        onView(withId(R.id.authorize_card_details_card_number_editText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_card_details_card_holder_name_editText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_card_details_expiry_editText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_card_details_cvc_editText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_card_details_cta))
            .check(matches(isDisplayed()))
    }

    fun enterCardNumber(cardNumber: String) = apply {
        onView(withId(R.id.authorize_card_details_card_number_editText))
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(typeText(cardNumber))
            .perform(closeSoftKeyboard())
    }

    fun enterCardHolderName(cardHolderName: String) = apply {
        onView(withId(R.id.authorize_card_details_card_holder_name_editText))
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(typeText(cardHolderName))
            .perform(closeSoftKeyboard())
    }

    fun enterExpiryDate(expiryDate: String) = apply {
        onView(withId(R.id.authorize_card_details_expiry_editText))
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(typeText(expiryDate))
            .perform(closeSoftKeyboard())
    }

    fun enterCvc(cvc: String) = apply {
        onView(withId(R.id.authorize_card_details_cvc_editText))
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(typeText(cvc))
            .perform(closeSoftKeyboard())
    }

    fun pressCta() = apply {
        onView(withId(R.id.authorize_card_details_cta))
            .check(matches(ViewMatchers.isDisplayed()))
            .perform(click())
    }
}