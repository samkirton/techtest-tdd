package com.worldpay.techtest.tests.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.worldpay.techtest.R

class AddressDetailsRobot {

    fun verifyAddressDetailsView() {
        onView(withId(R.id.authorize_address_details_address1_editText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_address_details_address2_editText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_address_details_county_editText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_address_details_post_code_editText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_address_details_country_code_editText))
            .check(matches(isDisplayed()))

        onView(withId(R.id.authorize_address_details_cta))
            .check(matches(isDisplayed()))
    }

    fun enterAddress1(address1: String) = apply {
        onView(withId(R.id.authorize_address_details_address1_editText))
            .check(matches(isDisplayed()))
            .perform(typeText(address1))
            .perform(closeSoftKeyboard())
    }

    fun enterAddress2(address2: String) = apply {
        onView(withId(R.id.authorize_address_details_address2_editText))
            .check(matches(isDisplayed()))
            .perform(typeText(address2))
            .perform(closeSoftKeyboard())
    }


    fun enterCounty(country: String) = apply {
        onView(withId(R.id.authorize_address_details_county_editText))
            .check(matches(isDisplayed()))
            .perform(typeText(country))
            .perform(closeSoftKeyboard())
    }

    fun enterPostCode(country: String) = apply {
        onView(withId(R.id.authorize_address_details_post_code_editText))
            .check(matches(isDisplayed()))
            .perform(typeText(country))
            .perform(closeSoftKeyboard())
    }

    fun enterCountryCode(country: String) = apply {
        onView(withId(R.id.authorize_address_details_country_code_editText))
            .check(matches(isDisplayed()))
            .perform(typeText(country))
            .perform(closeSoftKeyboard())
    }

    fun pressCta() = apply {
        onView(withId(R.id.authorize_address_details_cta))
            .check(matches(isDisplayed()))
            .perform(click())
    }
}