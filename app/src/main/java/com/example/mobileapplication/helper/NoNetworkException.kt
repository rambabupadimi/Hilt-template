package com.example.mobileapplication.helper

import android.content.Context
import java.io.IOException

class NoNetworkException(context: Context) : IOException() {
    private val mContext: Context = context

    override val message: String?
        get() = "No Internet"
}