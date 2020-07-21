package com.example.mobileapplication.data.remote

import com.example.mobileapplication.CommentsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteRepository @Inject constructor(private  val apiService: ApiService) {

    fun getAnswersFlow(apiKey:String, searchKey:String, page:Int): Flow<CommentsResponse> {
        return flow {
            emit(apiService?.getMovies(apiKey,searchKey,page));
        }.flowOn(Dispatchers.IO)
    }


}