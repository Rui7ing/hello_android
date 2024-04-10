package com.thoughtworks.androidtrain.view

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.view.fragments.AndroidFragment
import com.thoughtworks.androidtrain.view.fragments.JavaFragment
import com.thoughtworks.androidtrain.util.FragmentUtil

class LanguageSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_selection_layout)

        val androidButton = findViewById<Button>(R.id.AndroidButton)
        androidButton.setOnClickListener {
            FragmentUtil().replaceToFragment(this, R.id.whiteBackGround, AndroidFragment())
            Toast.makeText(this, "android", Toast.LENGTH_SHORT).show()
        }

        val javaButton = findViewById<Button>(R.id.JavaButton)
        javaButton.setOnClickListener {
            FragmentUtil().replaceToFragment(this, R.id.whiteBackGround, JavaFragment())
            Toast.makeText(this, "java", Toast.LENGTH_SHORT).show()
        }


    }
}