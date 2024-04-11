package com.thoughtworks.androidtrain.model.repositories

import androidx.lifecycle.LiveData
import com.thoughtworks.androidtrain.model.entity.Tweet


interface DataSource {
    fun fetchTweets(): LiveData<List<Tweet>>
}