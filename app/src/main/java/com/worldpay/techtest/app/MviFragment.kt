package com.worldpay.techtest.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.memtrip.mxandroid.MxRenderAction
import com.memtrip.mxandroid.MxViewFragment
import com.memtrip.mxandroid.MxViewIntent
import com.memtrip.mxandroid.MxViewLayout
import com.memtrip.mxandroid.MxViewState

abstract class MviFragment<VI : MxViewIntent, RA : MxRenderAction, VS : MxViewState, VL : MxViewLayout>
    : MxViewFragment<VI, RA, VS, VL>() {

    protected inline fun <reified T : ViewModel> getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
        ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}