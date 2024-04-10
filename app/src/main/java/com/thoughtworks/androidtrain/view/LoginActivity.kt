package com.thoughtworks.androidtrain.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.androidtrain.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
    }
}