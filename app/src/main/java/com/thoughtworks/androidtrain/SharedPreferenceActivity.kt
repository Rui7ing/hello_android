package com.thoughtworks.androidtrain

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class SharedPreferenceActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shared_preference_layout)
        val button = findViewById<Button>(R.id.hintButton)
        button.setOnClickListener {
            button.visibility = View.GONE
            findViewById<TextView>(R.id.hintText).visibility = View.GONE
            findViewById<TextView>(R.id.spTest).visibility = View.VISIBLE
        }

    }
}