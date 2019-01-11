package com.worldpay.techtest.app

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseInjectorApplication : DaggerApplication(), HasSupportFragmentInjector {

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun applicationInjector(): AndroidInjector<BaseInjectorApplication> = appComponent

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    abstract val appComponent: AppComponent
}