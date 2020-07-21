package com.example.mobileapplication.ui

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileapplication.CommentsResponse
import com.example.mobileapplication.data.remote.RemoteRepository
import com.example.mobileapplication.helper.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RemoteViewModel @ViewModelInject constructor(
                                    private val  remoteRepository: RemoteRepository,
                                    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var commentsResponse: MediatorLiveData<Resource<CommentsResponse>> = MediatorLiveData()

    fun getComments(page:Int, apiKey: String, searchKey: String) {
        viewModelScope.launch {
            remoteRepository.getAnswersFlow(apiKey,searchKey,page)
                .onStart {
                   commentsResponse.value =  Resource.loading(data = null)
                }.catch {
                    commentsResponse.value = Resource.error(data = null, message = it.message ?: "error")
                }
                .onCompletion {
                }.collect {
                    commentsResponse.value = Resource.success(it)
                }

        }
    }
}