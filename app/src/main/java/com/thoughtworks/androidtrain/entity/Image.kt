package com.thoughtworks.androidtrain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thoughtworks.androidtrain.model.SenderModel

@Entity
data class Image(
    @ColumnInfo var url: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int=0
}
