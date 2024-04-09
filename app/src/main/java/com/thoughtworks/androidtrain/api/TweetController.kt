package com.thoughtworks.androidtrain.api

import com.thoughtworks.androidtrain.entity.Tweet
import retrofit2.Response
import retrofit2.http.GET

interface TweetController {
    @GET("tweets.json")
    suspend fun fetchTweets(): Response<List<Tweet>>
}