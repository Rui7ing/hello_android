package com.thoughtworks.androidtrain

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.thoughtworks.androidtrain.repositories.HintRepository
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {

    private lateinit var hintRepository: HintRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_store_layout)
        val button = findViewById<Button>(R.id.knowButton)
        hintRepository = HintRepository(this)

        checkIsHintShown(button)
        button.setOnClickListener {
            hideHint(button)
            lifecycleScope.launch { hintRepository.setIsHintShown(true) }
        }

    }

    private fun checkIsHintShown(button: Button) {
        hintRepository.getIsHintShown().asLiveData().observe(this) {
            if (it) { hideHint(button) }
        }
    }

    private fun hideHint(button: Button) {
        button.visibility = View.GONE
        findViewById<TextView>(R.id.hintText).visibility = View.GONE
        findViewById<TextView>(R.id.spTest).visibility = View.VISIBLE
    }
}