package com.worldpay.techtest

import android.app.Application
import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.worldpay.techtest.api.ApiConfig
import com.worldpay.techtest.api.StubInterceptor
import com.worldpay.techtest.api.StubScenario
import com.worldpay.techtest.app.DaggerAppComponent
import com.worldpay.techtest.app.DemoActivity
import java.util.*

class StubActivityTestRule : ActivityTestRule<DemoActivity>(
    DemoActivity::class.java,
    true,
    false
) {

    private lateinit var stubScenario: StubScenario

    fun launch(stubScenario: StubScenario) {
        this.stubScenario = stubScenario
        launchActivity(null)
    }

    private fun inject() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as Application
        val injector = DaggerAppComponent
            .builder()
            .apiConfig(ApiConfig(Arrays.asList(StubInterceptor(stubScenario, context))))
            .application(context)
            .build()

        TestInjectorApplication.setInjector(injector)
    }

    override fun beforeActivityLaunched() {
        super.beforeActivityLaunched()
        inject()
    }

    override fun afterActivityFinished() {
        super.afterActivityFinished()
        TestInjectorApplication.resetInjector()
    }
}