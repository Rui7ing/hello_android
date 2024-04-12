package com.thoughtworks.androidtrain.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.thoughtworks.androidtrain.model.databases.ApplicationDatabase
import com.thoughtworks.androidtrain.model.entity.Tweet
import com.thoughtworks.androidtrain.model.repositories.TweetRepository
import com.thoughtworks.androidtrain.viewModel.TweetViewModel

class TweetsComposeActivity : AppCompatActivity() {

    private val repository: TweetRepository by lazy { TweetRepository(ApplicationDatabase(application).tweetDao()) }
    private val tweetViewModel: TweetViewModel by lazy { TweetViewModel(application, repository) }
    private var tweets = MutableLiveData<List<Tweet>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tweetViewModel.pullData()
        tweetViewModel.tweets.observe(this) {
            tweets.value = it
        }
        setContent {
            Text(text = "ssssssss")
            Content()
        }
    }

    @Composable
    private fun Content() {
        Column {
            tweets.value?.forEach {
                TweetItem(it)
            }
        }

    }

    @Composable
    fun TweetItem(tweet: Tweet) {
        println(tweet)
        Row {
            Image(
                modifier = Modifier.padding(all = 10.dp),
                imageVector = Icons.Rounded.AccountBox,
                contentDescription = "avatar"
            )
            Column {
                Text(
                    modifier = Modifier.padding(vertical = 2.dp),
                    text = "nick name",
                    color = Color.White
                )
                Text(
                    modifier = Modifier.padding(vertical = 2.dp),
                    text = "content",
                    color = Color.White
                )
            }
        }
    }


    @Preview
    @Composable
    fun ContentPreview() {
        Content()
    }

}
