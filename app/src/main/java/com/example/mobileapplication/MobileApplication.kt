package com.example.mobileapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobileApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}