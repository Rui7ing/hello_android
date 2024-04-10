package com.thoughtworks.androidtrain.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.thoughtworks.androidtrain.databases.ApplicationDatabase
import com.thoughtworks.androidtrain.entity.Tweet
import com.thoughtworks.androidtrain.repositories.TweetRepository
import kotlinx.coroutines.launch

class TweetViewModel(application: Application) : AndroidViewModel(application) {

    private val tweetRepository: TweetRepository by lazy {
        TweetRepository(ApplicationDatabase(application).tweetDao()) }

    val tweets: LiveData<List<Tweet>> by lazy {
        tweetRepository.fetchTweets().asLiveData()
    }

    fun pullData() {
        viewModelScope.launch {
            tweetRepository.saveFromRemote()
        }
    }
}
