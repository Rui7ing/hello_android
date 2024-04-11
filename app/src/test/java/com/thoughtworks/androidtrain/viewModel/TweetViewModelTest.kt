package com.thoughtworks.androidtrain.viewModel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.thoughtworks.androidtrain.model.databases.ApplicationDatabase
import com.thoughtworks.androidtrain.model.entity.Image
import com.thoughtworks.androidtrain.model.entity.Sender
import com.thoughtworks.androidtrain.model.entity.Tweet
import com.thoughtworks.androidtrain.model.repositories.TweetRepository
import net.bytebuddy.utility.RandomString
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TweetViewModelTest {

    private val applicationMock: Application = Mockito.mock(Application::class.java)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private val tweetRepository = TweetRepository(ApplicationDatabase(applicationMock).tweetDao())

    @Test fun should_fetch_right_data() {
        val mockData = getLiveData(getFakeTweetList())
        `when`(tweetRepository.fetchTweets()).thenReturn(mockData)

        val actual = TweetViewModel(applicationMock, tweetRepository).fetchTweets().value
        val expect = mockData.value
        Assert.assertEquals(expect, actual)
    }

    private fun getFakeTweetList(): List<Tweet> {
        return listOf(
            Tweet(
                content = "content1",
                images = listOf(Image("url1")),
                sender = Sender("name1", "nick1", "url1"),
                comments = emptyList(),
                error = null,
                unknownError = null,
                date = RandomString().toString()
            ),
            Tweet(
                content = "content2",
                images = listOf(Image("url2")),
                sender = Sender("name2", "nick2", "url2"),
                comments = emptyList(),
                error = null,
                unknownError = null,
                date = RandomString().toString()
            )
        )
    }

    private fun getLiveData(list : List<Tweet>): MutableLiveData<List<Tweet>> {
        val liveData = MutableLiveData<List<Tweet>>()
        liveData.value = list
        return liveData
    }
}