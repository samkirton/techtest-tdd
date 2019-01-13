package com.worldpay.techtest.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.worldpay.techtest.R
import com.worldpay.techtest.app.authorize.AuthorizeFragment
import com.worldpay.techtest.app.authorize.model.ItemDetails

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_activity)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.demo_activity_fragment_container,
            AuthorizeFragment.newInstance(ItemDetails(
                "Elegant Objects",
                "TL;DR There are 23 practical recommendations for object-oriented programmers. Most of them are completely against everything you've read in other books. For example, static methods, NULL references, getters, setters, and mutable classes are called evil.",
                "elegant-objects",
                ItemDetails.Price("GBP", 19.99)
            ))
        )
        transaction.commit()
    }
}