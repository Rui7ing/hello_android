package com.thoughtworks.androidtrain;

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThreadActivity : AppCompatActivity() {

    private val button: Button by lazy { findViewById(R.id.thread_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.thread_layout)
        button.setOnClickListener {
            button.isClickable = false
            val someTask = SomeTask(button)
            someTask.execute()
        }
    }

    class SomeTask(private var button: Button) : AsyncTask<Void, Void, String>() {
        private var time = 0

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg p0: Void?): String {
            while (time < 10) {
                Thread.sleep(1000)
                time++
                publishProgress()
            }
            return time.toString()
        }

        @Deprecated("Deprecated in Java")
        override fun onProgressUpdate(vararg values: Void?) {
            button.text = time.toString()
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: String?) {
            button.isClickable = true
        }
    }
}
