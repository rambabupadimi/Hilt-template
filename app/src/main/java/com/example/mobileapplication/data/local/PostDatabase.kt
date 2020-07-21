package com.example.mobileapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobileapplication.data.local.posts.PostDAO
import com.example.mobileapplication.data.local.posts.PostEntity

@Database(entities = [PostEntity::class],version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postsDAO() : PostDAO

    companion object {
        @Volatile
        private var INSTANCE: PostDatabase? = null

        fun getDatabase(
            context: Context
        ): PostDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostDatabase::class.java,
                    "posts_database"
                )
                    // .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}