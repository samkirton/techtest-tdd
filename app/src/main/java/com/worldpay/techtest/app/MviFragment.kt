package com.worldpay.techtest.app

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.memtrip.mxandroid.*

abstract class MviFragment<VI : MxViewIntent, RA : MxRenderAction, VS : MxViewState, VL : MxViewLayout>
    : MxViewFragment<VI, RA, VS, VL>() {

    internal fun hideKeyboard() {
        val inputMethodManager = context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow((context!! as AppCompatActivity).window.decorView.windowToken, 0)
    }

    protected inline fun <reified T : ViewModel> getViewModel(viewModelFactory: ViewModelProvider.Factory): T =
        ViewModelProviders.of(this, viewModelFactory)[T::class.java]
}