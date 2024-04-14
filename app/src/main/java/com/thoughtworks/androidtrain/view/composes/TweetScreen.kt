package com.thoughtworks.androidtrain.view.composes

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.thoughtworks.androidtrain.model.entity.Tweet


@Composable
fun LoadTweets(tweets: List<Tweet>) {
    Column(
        modifier = Modifier.verticalScroll(state = ScrollState(1), enabled = true)
    ) {
        tweets.forEach { TweetItem(tweet = it) }
        BottomItem()
    }
}


@Composable
fun TweetItem(tweet: Tweet) {
    val openDialog = remember { mutableStateOf(false) }
    DiaLogItem(openDialog, tweet)
    Row {
        AsyncImage(
            modifier = Modifier
                .padding(all = 5.dp)
                .clickable {
                    openDialog.value = true
                }
                .size(60.dp),
            model = tweet.sender?.avatar,
            contentDescription = null
        )
        Column {
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = tweet.sender?.nick ?: "",
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(vertical = 2.dp),
                text = tweet.content ?: "",
                color = Color.Black
            )
        }
    }
}

@Composable
private fun DiaLogItem(
    openDialog: MutableState<Boolean>,
    tweet: Tweet
) {
    if (!openDialog.value) {
        return
    }
    Dialog(onDismissRequest = { openDialog.value = false }) {
        AsyncImage(
            modifier = Modifier
                .padding(all = 5.dp)
                .clickable {
                    openDialog.value = true
                }
                .size(300.dp),
            model = tweet.sender?.avatar,
            contentDescription = null
        )
    }
}

@Composable
fun BottomItem() {
    Text(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(vertical = 10.dp)
        ,
        text = "到底了",
        textAlign = TextAlign.Center
    )
}