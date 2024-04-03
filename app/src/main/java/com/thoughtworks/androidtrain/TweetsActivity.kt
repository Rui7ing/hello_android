package com.thoughtworks.androidtrain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.androidtrain.model.Tweet
import com.thoughtworks.androidtrain.util.JsonUtil

class TweetsActivity : AppCompatActivity() {

    val recyclerView: RecyclerView by lazy { findViewById(R.id.recycleView) }
    val tweets: List<Tweet> by lazy { JsonUtil().getTweetList() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)
    }
}
