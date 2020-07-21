package com.example.mobileapplication.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.mobileapplication.data.remote.ApiEndPoint
import com.example.mobileapplication.helper.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun  provideGSON(@ApplicationContext context: Context): Gson {
        val gsonBuilder = GsonBuilder();
        return  gsonBuilder.create()
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context, preferences: SharedStoragePreferences) : OkHttpClient {
        val logging = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .connectTimeout(800, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)

        client.addInterceptor(logging)

        client.addInterceptor((object : NetworkInterceptor() {
            override val isInternetAvailable: Boolean
                get() = isNetworkConnected(context)

            override fun onInternetUnavailable() {
                throw NoNetworkException(context)
            }
        }))

        client.addInterceptor(
            AuthenticationInterceptor(
                CommonUtils.getDeviceId(),
                preferences

            )
        )
        return client.build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,gson: Gson) : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(ApiEndPoint.BASE_URL)
            .client(okHttpClient)
            .build()
    }



    fun isNetworkConnected(@ApplicationContext context: Context?): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}