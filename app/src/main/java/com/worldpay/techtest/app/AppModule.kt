package com.worldpay.techtest.app

import com.worldpay.techtest.app.authorize.AuthorizeFragment
import com.worldpay.techtest.app.authorize.AuthorizeFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [AuthorizeFragmentModule::class])
    internal abstract fun contributeAuthorizeFragment(): AuthorizeFragment
}