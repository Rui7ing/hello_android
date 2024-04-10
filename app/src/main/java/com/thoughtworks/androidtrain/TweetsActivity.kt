package com.thoughtworks.androidtrain

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.thoughtworks.androidtrain.adapter.TweetAdapter
import com.thoughtworks.androidtrain.viewModel.TweetViewModel

class TweetsActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerView) }
    private val refresh: SwipeRefreshLayout by lazy { findViewById(R.id.swiperefresh) }
    private val tweetAdapter: TweetAdapter by lazy { TweetAdapter(emptyList(), this) }
    private val tweetViewModel: TweetViewModel by lazy { TweetViewModel(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)
        initTweets()
        initUI()
    }

    private fun initTweets() {
        tweetViewModel.pullData()
        tweetViewModel.tweets.observe(this) { tweets ->
            tweetAdapter.tweets = tweets.sortedByDescending { it.date }
        }
    }

    private fun initUI() {
        recyclerView.adapter = tweetAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        refresh.setOnRefreshListener {
            tweetAdapter.tweets = tweetAdapter.tweets.sortedByDescending { it.date }
            recyclerView.adapter = tweetAdapter
            Handler().postDelayed({ refresh.isRefreshing = false }, 2000)
        }
    }
}
