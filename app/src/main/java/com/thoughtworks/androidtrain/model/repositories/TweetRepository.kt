package com.thoughtworks.androidtrain.model.repositories

import androidx.lifecycle.LiveData
import com.thoughtworks.androidtrain.model.api.TweetApi
import com.thoughtworks.androidtrain.model.dao.TweetDao
import com.thoughtworks.androidtrain.model.entity.Tweet

class TweetRepository(private val tweetDao: TweetDao) : DataSource {
    override fun fetchTweets(): LiveData<List<Tweet>> = tweetDao.getAll()

    suspend fun saveFromRemote() {
        val tweets = TweetApi.fetchTweets().filter { it.isValid() }
        tweets.forEach(Tweet::generateAndBindId)
        tweetDao.insertAll(tweets)
    }
}