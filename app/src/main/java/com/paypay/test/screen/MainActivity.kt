package com.paypay.test.screen

import android.os.Bundle
import com.paypay.test.screen.conversation.CurrencyConversionFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, CurrencyConversionFragment())
            .commit()
    }
}