package com.thoughtworks.androidtrain.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.thoughtworks.androidtrain.model.entity.Tweet
import com.thoughtworks.androidtrain.model.repositories.TweetRepository
import kotlinx.coroutines.launch

class TweetViewModel(application: Application, private val tweetRepository: TweetRepository) : AndroidViewModel(application) {

    val tweets = MutableLiveData<List<Tweet>>()

    fun fetchTweets(): MutableLiveData<List<Tweet>> {
        tweets.postValue(tweetRepository.fetchTweets().value)
        return tweets
    }

    fun pullData() {
        viewModelScope.launch {
            tweetRepository.saveFromRemote()
            tweets.value = tweetRepository.fetchTweets().value
        }
    }
}
