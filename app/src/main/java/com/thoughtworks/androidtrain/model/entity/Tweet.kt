package com.thoughtworks.androidtrain.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.thoughtworks.androidtrain.util.PermissionUtil

@Entity
data class Tweet(
    @ColumnInfo var content: String?,
    @ColumnInfo var images: List<Image>?,
    @ColumnInfo var sender: Sender?,
    @ColumnInfo var cøomments: List<Comment>?,
    @ColumnInfo var error: String?,
    @ColumnInfo @SerializedName("unknown error") var unknownError: String?,
    @ColumnInfo var date: String?,
) {
    @PrimaryKey
    @ColumnInfo
    var id: String = ""

    override fun toString(): String {
        return "content $content \n"
    }

    fun isValid(): Boolean = error.isNullOrEmpty() && unknownError.isNullOrEmpty()

    fun generateAndBindId() {
        this.id = PermissionUtil().md5(sender?.nick + date)
    }
}
