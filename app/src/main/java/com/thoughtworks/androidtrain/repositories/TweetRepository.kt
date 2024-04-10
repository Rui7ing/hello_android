package com.thoughtworks.androidtrain.repositories

import com.thoughtworks.androidtrain.api.TweetService
import com.thoughtworks.androidtrain.dao.TweetDao
import com.thoughtworks.androidtrain.entity.Tweet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TweetRepository(private val tweetDao: TweetDao) : DataSource {
    override fun fetchTweets(): Flow<List<Tweet>> = flow {
        emit(tweetDao.getAll())
    }

    suspend fun saveFromRemote() {
        val tweets = TweetService.fetchTweets().filter { it.isValid() }
        tweets.forEach(Tweet::generateAndBindId)
        tweetDao.insertAll(tweets)
    }
}