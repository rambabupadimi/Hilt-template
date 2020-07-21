package com.example.mobileapplication.data.remote

import com.example.mobileapplication.CommentsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface  ApiService {

    @GET(ApiEndPoint.BREATHINGS)
    suspend fun getMovies(@Query("apikey") apiKey: String, @Query("s") searchKey:String, @Query("page") page:Int) : CommentsResponse


}