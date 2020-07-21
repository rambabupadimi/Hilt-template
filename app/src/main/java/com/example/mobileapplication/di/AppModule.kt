package com.example.mobileapplication.di

import android.content.Context
import androidx.room.Room
import com.example.mobileapplication.data.local.LocalRepository
import com.example.mobileapplication.data.local.PostDatabase
import com.example.mobileapplication.data.remote.ApiService
import com.example.mobileapplication.data.remote.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit) : ApiService {
        return  retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(apiService: ApiService) : RemoteRepository {
        return RemoteRepository(apiService)
    }



    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context?): PostDatabase {
        return Room.databaseBuilder<PostDatabase>(
            context!!,
            PostDatabase::class.java,
            "post_database_one")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideLocalRepository(database: PostDatabase) : LocalRepository {
        return LocalRepository(database)
    }



}