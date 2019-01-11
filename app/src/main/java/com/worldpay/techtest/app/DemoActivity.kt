package com.worldpay.techtest.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.worldpay.techtest.R
import com.worldpay.techtest.app.authorize.AuthorizeFragment

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_activity)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.demo_activity_fragment_container,
            AuthorizeFragment.newInstance()
        )
        transaction.commit()
    }
}