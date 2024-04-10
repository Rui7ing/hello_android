package com.thoughtworks.androidtrain.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.thoughtworks.androidtrain.model.entity.Image

@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    fun getAll(): List<Image>

    @Query("SELECT * FROM image WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Image>

    @Insert
    fun insertAll(image: List<Image>)

    @Delete
    fun delete(image: Image)
}