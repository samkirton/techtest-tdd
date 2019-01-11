package com.worldpay.techtest.app

import com.worldpay.techtest.api.ApiConfig

class InjectorApplication : BaseInjectorApplication() {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .apiConfig(ApiConfig())
            .application(this)
            .build()
    }
}