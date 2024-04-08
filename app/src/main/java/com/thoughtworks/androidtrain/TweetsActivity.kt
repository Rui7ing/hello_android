package com.thoughtworks.androidtrain

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thoughtworks.androidtrain.adapter.TweetAdapter
import com.thoughtworks.androidtrain.model.TweetModel
import com.thoughtworks.androidtrain.util.JsonUtil

class TweetsActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val tweetModels: List<TweetModel> by lazy {
        val tweetsJsonStr = JsonUtil().getJsonStr(resources, R.raw.tweets)
        JsonUtil().getTweetListFromJsonStr(tweetsJsonStr)
    }
    private val refresh: SwipeRefreshLayout by lazy { findViewById(R.id.swiperefresh) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)
        initEvent()
    }

    private fun initEvent() {
        recyclerView.adapter = TweetAdapter(tweetModels.filter { it.isValid() }, this)
        recyclerView.layoutManager = LinearLayoutManager(this)

        refresh.setOnRefreshListener {
            recyclerView.adapter = TweetAdapter(tweetModels.shuffled().filter { it.isValid() }, this)
            Handler().postDelayed({ refresh.isRefreshing = false }, 2000)
        }
    }
}
