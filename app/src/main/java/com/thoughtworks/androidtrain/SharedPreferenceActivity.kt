package com.thoughtworks.androidtrain

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

private const val HINT_KEY = "isHintShown"

class SharedPreferenceActivity : Activity() {

    private val sp: SharedPreferences by lazy {
        this.getSharedPreferences(
            HINT_KEY,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shared_preference_layout)
        val button = findViewById<Button>(R.id.hintButton)
        if (getIsHintShown()) {
            button.visibility = View.GONE
            findViewById<TextView>(R.id.hintText).visibility = View.GONE
            findViewById<TextView>(R.id.spTest).visibility = View.VISIBLE
        }

        button.setOnClickListener {
            saveIsHintShown()
            button.visibility = View.GONE
            findViewById<TextView>(R.id.hintText).visibility = View.GONE
            findViewById<TextView>(R.id.spTest).visibility = View.VISIBLE
        }

    }

    private fun getIsHintShown() = sp.getBoolean(HINT_KEY, false)

    @SuppressLint("CommitPrefEdits")
    private fun saveIsHintShown() {
        val editor = sp.edit()
        editor.putBoolean(HINT_KEY, true)
        editor.commit()
    }
}