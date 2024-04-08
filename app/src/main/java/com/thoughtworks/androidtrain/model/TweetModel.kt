package com.thoughtworks.androidtrain.model

import com.google.gson.annotations.SerializedName

data class TweetModel(
    val content: String,
    val images: List<ImageModel>,
    val sender: SenderModel,
    val comments: List<CommentModel>,
    val error: String?,
    @SerializedName("unknown error") var unknownError: String?

) {
    override fun toString(): String {
        return "content $content \n"
    }

    fun isValid(): Boolean = error.isNullOrEmpty() && unknownError.isNullOrEmpty()
}