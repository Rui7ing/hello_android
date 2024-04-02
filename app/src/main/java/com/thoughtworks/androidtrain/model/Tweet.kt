package com.thoughtworks.androidtrain.model

import com.google.gson.annotations.SerializedName

data class Tweet(
    val content: String,
    val images: List<Image>,
    val sender: Sender,
    val comments: List<Comment>,
    val error: String,
    @SerializedName("unknown error") var unknownError: String

) {
    override fun toString(): String {
        return "content $content \n"
    }
}