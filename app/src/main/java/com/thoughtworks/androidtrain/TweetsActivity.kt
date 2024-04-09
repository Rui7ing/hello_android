package com.thoughtworks.androidtrain

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thoughtworks.androidtrain.adapter.TweetAdapter
import com.thoughtworks.androidtrain.api.RetrofitBuilder.retrofit
import com.thoughtworks.androidtrain.databases.ApplicationDatabase
import com.thoughtworks.androidtrain.entity.Tweet
import com.thoughtworks.androidtrain.repositories.TweetRepository
import com.thoughtworks.androidtrain.util.JsonUtil
import kotlinx.coroutines.launch

private const val DATA_INPUT_KEY = "isJsonDataInserted"
class TweetsActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val tweets: List<Tweet> by lazy {
        val tweetsJsonStr = JsonUtil().getJsonStr(resources, R.raw.tweets)
        JsonUtil().getTweetListFromJsonStr(tweetsJsonStr)
    }
    private val refresh: SwipeRefreshLayout by lazy { findViewById(R.id.swiperefresh) }
    private val tweetAdapter: TweetAdapter by lazy { TweetAdapter(emptyList(), this) }
    private val database by lazy { ApplicationDatabase(this) }
    private val sp: SharedPreferences by lazy {
        this.getSharedPreferences(DATA_INPUT_KEY, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)
        initTweets()
        initUI()
    }

    private fun initTweets() {

        lifecycleScope.launch {
            try {
                val response = retrofit.fetchTweets()
                if (response.isSuccessful && !sp.getBoolean(DATA_INPUT_KEY, false)) {
                    lifecycleScope.launch { database.tweetDao().insertAll(tweets) }
                    saveIsJsonDataInserted()
                }
            } catch (e: Exception) {
                Toast.makeText(this@TweetsActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launch {
            TweetRepository(database.tweetDao()).fetchTweets().collect { tweetList ->
                tweetAdapter.tweets = tweetList.filter { it.isValid() }
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveIsJsonDataInserted() {
        val editor = sp.edit()
        editor.putBoolean(DATA_INPUT_KEY, true)
        editor.apply()
    }

    private fun initUI() {
        recyclerView.adapter = tweetAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        refresh.setOnRefreshListener {
            tweetAdapter.tweets = tweetAdapter.tweets.shuffled()
            recyclerView.adapter = tweetAdapter
            Handler().postDelayed({ refresh.isRefreshing = false }, 2000)
        }
    }
}
