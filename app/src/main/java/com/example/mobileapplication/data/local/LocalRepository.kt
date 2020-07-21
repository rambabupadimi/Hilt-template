package com.example.mobileapplication.data.local

import androidx.lifecycle.LiveData
import com.example.mobileapplication.data.local.posts.PostDAO
import com.example.mobileapplication.data.local.posts.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepository @Inject constructor(private  val database: PostDatabase) {

    private var postsDao: PostDAO = database.postsDAO()

    suspend  fun insertPosts(post: PostEntity) : Long{
        return postsDao.insertPosts(post)
    }

    fun getPosts() : Flow<List<PostEntity>> {
        return postsDao.getPosts()
    }

}