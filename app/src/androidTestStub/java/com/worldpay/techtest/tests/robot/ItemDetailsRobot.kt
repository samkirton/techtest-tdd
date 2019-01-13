package com.worldpay.techtest.tests.robot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.worldpay.techtest.R

class ItemDetailsRobot {

    fun verifyItemDetailsView() = apply {
        onView(withId(R.id.authorize_item_details_name_label))
            .check(matches(isDisplayed()))
        onView(withId(R.id.authorize_item_details_price_label))
            .check(matches(isDisplayed()))
        onView(withId(R.id.authorize_item_details_description_label))
            .check(matches(isDisplayed()))
        onView(withId(R.id.authorize_item_details_cta))
            .check(matches(isDisplayed()))
    }

    fun pressCta() = apply {
        onView(withId(R.id.authorize_item_details_cta))
            .check(matches(isDisplayed()))
            .perform(click())
    }
}