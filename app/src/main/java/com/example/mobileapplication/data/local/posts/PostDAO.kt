package com.example.mobileapplication.data.local.posts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface PostDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(post: PostEntity) : Long

    @Query("SELECT * FROM post")
    fun getPosts() : Flow<List<PostEntity>>
}