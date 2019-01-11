package com.worldpay.techtest

import com.worldpay.techtest.api.ApiConfig
import com.worldpay.techtest.app.AppComponent
import com.worldpay.techtest.app.BaseInjectorApplication
import com.worldpay.techtest.api.StubInterceptor
import com.worldpay.techtest.api.StubScenario
import com.worldpay.techtest.app.DaggerAppComponent
import dagger.android.AndroidInjector
import java.util.Arrays.asList

class TestInjectorApplication : BaseInjectorApplication() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    private fun inject() {
        applicationInjector().inject(this)
    }

    override fun applicationInjector(): AndroidInjector<BaseInjectorApplication> {
        return androidInjector ?: super.applicationInjector()
    }


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

    companion object {

        lateinit var instance: TestInjectorApplication
        private var androidInjector: AndroidInjector<BaseInjectorApplication>? = null

        fun setInjector(injector: AndroidInjector<BaseInjectorApplication>) {
            androidInjector = injector
            instance.inject()
        }

        fun resetInjector() {
            androidInjector = null
            instance.inject()
        }
    }
}