package com.example.mobileapplication.di

import android.content.Context
import com.example.mobileapplication.helper.SharedStoragePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object StorageModule {

    @Singleton
    @Provides
    fun  provideStorage(@ApplicationContext context: Context) : SharedStoragePreferences {
        return SharedStoragePreferences(context)
    }

}