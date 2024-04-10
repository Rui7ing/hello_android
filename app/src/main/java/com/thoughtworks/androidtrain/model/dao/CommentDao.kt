package com.thoughtworks.androidtrain.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.androidtrain.model.entity.Comment

@Dao
interface CommentDao {
    @Query("SELECT * FROM comment")
    fun getAll(): List<Comment>

    @Query("SELECT * FROM comment WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Comment>

    @Insert
    fun insertAll(comments: List<Comment>)

    @Delete
    fun delete(comment: Comment)
}