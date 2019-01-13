package com.worldpay.techtest.uikit

import android.view.View
import android.widget.EditText

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun EditText.isNotEmpty(): Boolean = text.toString().trim().isNotEmpty()

fun EditText.string(): String = text.toString().trim()

fun EditText.range(start: Int, end: Int): String = text.substring(start, end)

fun EditText.length(): Int = text.toString().length