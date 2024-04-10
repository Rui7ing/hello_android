package com.thoughtworks.androidtrain.api

import com.thoughtworks.androidtrain.api.RetrofitBuilder.retrofit
import com.thoughtworks.androidtrain.entity.Tweet
import retrofit2.Response
import retrofit2.http.GET

object TweetService {

    suspend fun fetchTweets(): List<Tweet> {
        return if (retrofit.fetchTweets().isSuccessful) {
            retrofit.fetchTweets().body() ?: emptyList()
        } else { emptyList() }
    }
}

interface ITweetService {
    @GET("tweets.json")
    suspend fun fetchTweets(): Response<List<Tweet>>
}