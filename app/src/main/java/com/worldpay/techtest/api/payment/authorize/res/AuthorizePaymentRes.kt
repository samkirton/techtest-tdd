package com.worldpay.techtest.api.payment.authorize.res

import com.squareup.moshi.Json
import com.worldpay.techtest.api.payment.CuryJson
import com.worldpay.techtest.api.payment.HrefJson

data class AuthorizePaymentRes(
    val outcome: String,
    @Json(name = "_links") val links: Links
) {
    data class Links(
        @Json(name = "payments:cancel") val cancel: HrefJson,
        @Json(name = "payments:settle") val settle: HrefJson,
        @Json(name = "payments:partialSettle") val partialSettle: HrefJson,
        @Json(name = "payments:events") val events: HrefJson,
        val curies: List<CuryJson>
    )
}