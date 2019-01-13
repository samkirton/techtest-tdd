package com.worldpay.techtest.uikit

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.worldpay.techtest.R

class AppButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.AppButton
) : AppCompatButton(context, attrs, defStyleAttr) {

    override fun drawableStateChanged() {
        super.drawableStateChanged()

        alpha = if (isEnabled) {
            1.0f
        } else {
            0.4f
        }
    }
}