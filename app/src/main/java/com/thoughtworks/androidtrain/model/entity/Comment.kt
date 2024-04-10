package com.thoughtworks.androidtrain.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
    ForeignKey(
        entity = Sender::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("sender_id"))
))
data class Comment(
    @ColumnInfo var content: String,
    @ColumnInfo(name = "sender_id") var senderId: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int=0
}
