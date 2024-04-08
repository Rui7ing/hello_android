package com.thoughtworks.androidtrain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
        ForeignKey(
            entity = Sender::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("sender_id"))))
data class Tweet(
    @ColumnInfo var content: String,
    @ColumnInfo(name = "sender_id") var senderId: Long,
    @ColumnInfo var error: String?,
    @ColumnInfo(name = "unknown_error") var unknownError: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
