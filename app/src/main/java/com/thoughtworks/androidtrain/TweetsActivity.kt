package com.thoughtworks.androidtrain

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thoughtworks.androidtrain.adapter.TweetAdapter
import com.thoughtworks.androidtrain.databases.ApplicationDatabase
import com.thoughtworks.androidtrain.entity.Tweet
import com.thoughtworks.androidtrain.repositories.TweetRepository
import com.thoughtworks.androidtrain.util.JsonUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val DATA_INPUT_KEY = "isJsonDataInserted"
class TweetsActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val tweets: List<Tweet> by lazy {
        val tweetsJsonStr = JsonUtil().getJsonStr(resources, R.raw.tweets)
        JsonUtil().getTweetListFromJsonStr(tweetsJsonStr)
    }
    private val refresh: SwipeRefreshLayout by lazy { findViewById(R.id.swiperefresh) }
    private val database by lazy { ApplicationDatabase(this) }
    private val sp: SharedPreferences by lazy {
        this.getSharedPreferences(
            DATA_INPUT_KEY,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)
        initEvent()
        initTweets()
    }

    private fun initTweets() {
        if (!sp.getBoolean(DATA_INPUT_KEY, false)) {
            GlobalScope.launch { database.tweetDao().insertAll(tweets) }
            saveIsJsonDataInserted()
        }
        this.let {
            GlobalScope.launch {
                TweetRepository(database.tweetDao()).fetchTweets()
                    .collect { data -> TweetAdapter(data, it) }
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveIsJsonDataInserted() {
        val editor = sp.edit()
        editor.putBoolean(DATA_INPUT_KEY, true)
        editor.apply()
    }

    private fun initEvent() {
        recyclerView.adapter = TweetAdapter(tweets.filter { it.isValid() }, this)
        recyclerView.layoutManager = LinearLayoutManager(this)

        refresh.setOnRefreshListener {
            recyclerView.adapter = TweetAdapter(tweets.shuffled().filter { it.isValid() }, this)
            Handler().postDelayed({ refresh.isRefreshing = false }, 2000)
        }
    }
}
