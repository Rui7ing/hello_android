package com.thoughtworks.androidtrain.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thoughtworks.androidtrain.dao.CommentDao
import com.thoughtworks.androidtrain.dao.ImageDao
import com.thoughtworks.androidtrain.dao.SenderDao
import com.thoughtworks.androidtrain.dao.TweetDao
import com.thoughtworks.androidtrain.entity.Comment
import com.thoughtworks.androidtrain.entity.Image
import com.thoughtworks.androidtrain.entity.Sender
import com.thoughtworks.androidtrain.entity.Tweet

@Database(
    entities = [Tweet::class, Image::class, Sender::class, Comment::class],
    version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun tweetDao(): TweetDao
    abstract fun imageDao(): ImageDao
    abstract fun senderDao(): SenderDao
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var inastance: ApplicationDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): ApplicationDatabase {
            return inastance ?: synchronized(LOCK) {
                inastance ?: buildDatabase(context).also {
                    inastance = it
                }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ApplicationDatabase::class.java, "android_train_db")
                .build()
    }
}