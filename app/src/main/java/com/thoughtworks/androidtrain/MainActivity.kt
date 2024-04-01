package com.thoughtworks.androidtrain

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private val radius = 20f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        layoutButton()
    }

    private fun layoutButton() {
        val layout = findViewById<LinearLayout>(R.id.linearLayoutButtons)

        val drawable = GradientDrawable()
        drawable.cornerRadius = radius
        drawable.color = ColorStateList.valueOf(Color.GRAY)

        for (i in 1..10) {
            val button = Button(this).also {
                val resourceId = resources.getIdentifier("button_$i", "string", packageName)
                it.text = resources.getText(resourceId)
                val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                layoutParams.width = 660
                layoutParams.topMargin = 16
                it.layoutParams = layoutParams
                it.background = drawable
            }
            when (i) {
                1 -> button.setOnClickListener { jumpToView(ConstraintActivity()) }
                2 -> button.setOnClickListener { jumpToView(LoginActivity()) }
                else -> {}
            }
            layout.addView(button)
        }
    }

    private fun jumpToView(activity : AppCompatActivity) {
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}