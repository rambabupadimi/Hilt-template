package com.example.mobileapplication.helper

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class AuthenticationInterceptor @Inject constructor(
    deviceId: String,
    preferences: SharedStoragePreferences
) : Interceptor {

    //    private val authToken = token
    private val device = deviceId
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        lateinit var builder: Request.Builder
        builder = original.newBuilder()

        // val authToken = preferences.getTokenFromPreference(Constants.AUTHORIZATION_TOKEN, "") ?: ""

        // if (authToken.isNotEmpty())
        //   builder.header("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiaWF0IjoxNTg5MzYzMDMyfQ.V4I3MsLuHpN0qeB9k2NzQOaoTfDdkctUQ0Qbu7B7qck")

        //   if (device.isNotEmpty())
        //     builder.header("Device-Token", device)


        val request = builder.build()

        return chain.proceed(request)
    }
}