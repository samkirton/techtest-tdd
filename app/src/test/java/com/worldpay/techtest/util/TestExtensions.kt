package com.worldpay.techtest.util

import io.reactivex.observers.TestObserver

fun <T> TestObserver<T>.get(index: Int): T = this.values()[index]