package com.worldpay.techtest.api.payment.cancel

import com.squareup.moshi.Json
import com.worldpay.techtest.api.payment.CuryJson
import com.worldpay.techtest.api.payment.HrefJson

data class CancelPaymentRes(
    val outcome: String,
    @Json(name = "_links") val links: Links
) {
    data class Links(
        @Json(name = "payments:events") val cancel: HrefJson,
        val curies: List<CuryJson>
    )
}