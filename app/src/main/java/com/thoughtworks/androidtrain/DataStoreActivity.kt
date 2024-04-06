package com.thoughtworks.androidtrain

import android.app.Activity
import android.os.Bundle

class DataStoreActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_store_layout)
    }
}