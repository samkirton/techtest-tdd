package com.worldpay.techtest.app

import com.worldpay.techtest.api.ApiConfig
import com.worldpay.techtest.api.StubInterceptor
import com.worldpay.techtest.api.StubScenario
import java.util.Arrays.asList

class InjectorApplication : BaseInjectorApplication() {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .apiConfig(ApiConfig(asList(
                StubInterceptor(
                    StubScenario(),
                    this
                )
            )))
            .application(this)
            .build()
    }
}