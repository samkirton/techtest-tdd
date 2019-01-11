package com.worldpay.techtest.api

import com.worldpay.techtest.api.payment.authorize.AuthorizeHandler
import com.worldpay.techtest.api.payment.cancel.CancelHandler

data class StubScenario(
    val authorize: AuthorizeHandler.Journey = AuthorizeHandler.Journey.Success,
    val cancel: CancelHandler.Journey = CancelHandler.Journey.GenericError
)