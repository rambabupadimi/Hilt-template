package com.example.mobileapplication.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplication.data.local.LocalRepository
import com.example.mobileapplication.data.local.posts.PostEntity
import com.example.mobileapplication.helper.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LocalViewModel @ViewModelInject  constructor(
                            private  val localRepository: LocalRepository,
                            @Assisted private val savedStateHandle: SavedStateHandle
):  ViewModel() {

    var postResponse : MutableLiveData<Resource<List<PostEntity>>> = MutableLiveData()

    fun insertPost(postEntity: PostEntity) {
        viewModelScope.launch {
            localRepository.insertPosts(postEntity)
        }
    }

     fun getPosts()  {
        viewModelScope.launch {
           localRepository.getPosts()
            .onStart {
               postResponse.value = Resource.loading(data = null)
            }
            .onCompletion {
            }.catch {
               postResponse.value = Resource.error(data = null, message = it.message ?: "error")
            }.collect {
               postResponse.value = Resource.success(it)
            }
        }

    }

}