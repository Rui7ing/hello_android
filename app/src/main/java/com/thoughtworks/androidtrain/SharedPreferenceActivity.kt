package com.thoughtworks.androidtrain

import android.app.Activity
import android.os.Bundle

class SharedPreferenceActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shared_preference_layout)
    }
}