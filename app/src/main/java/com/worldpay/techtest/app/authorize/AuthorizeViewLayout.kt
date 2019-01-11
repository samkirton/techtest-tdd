package com.worldpay.techtest.app.authorize

import com.memtrip.mxandroid.MxViewLayout

interface AuthorizeViewLayout : MxViewLayout {
    fun showProgress()
    fun showError()
}