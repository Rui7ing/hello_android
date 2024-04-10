package com.thoughtworks.androidtrain.model.repositories

import com.thoughtworks.androidtrain.model.api.TweetApi
import com.thoughtworks.androidtrain.model.dao.TweetDao
import com.thoughtworks.androidtrain.model.entity.Tweet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TweetRepository(private val tweetDao: TweetDao) : DataSource {
    override fun fetchTweets(): Flow<List<Tweet>> = flow {
        emit(tweetDao.getAll())
    }

    suspend fun saveFromRemote() {
        val tweets = TweetApi.fetchTweets().filter { it.isValid() }
        tweets.forEach(Tweet::generateAndBindId)
        tweetDao.insertAll(tweets)
    }
}