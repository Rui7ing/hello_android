package com.thoughtworks.androidtrain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.androidtrain.adapter.TweetAdapter
import com.thoughtworks.androidtrain.model.Tweet
import com.thoughtworks.androidtrain.util.JsonUtil

class TweetsActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val tweets: List<Tweet> by lazy { JsonUtil().getTweetList() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)
        initEvent()
    }

    private fun initEvent() {
        val nonErrorTweets = tweets.filter { it.error.isNullOrEmpty() && it.unknownError.isNullOrEmpty() }
        recyclerView.adapter = TweetAdapter(nonErrorTweets, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
