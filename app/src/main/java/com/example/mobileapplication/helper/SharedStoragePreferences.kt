package com.example.mobileapplication.helper

import android.content.Context
import javax.inject.Inject

class SharedStoragePreferences @Inject constructor(context: Context) : Storage {

    private val sharedPreferences = context.getSharedPreferences("Dagger", Context.MODE_PRIVATE)

    override fun getString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String) : String {
        return sharedPreferences.getString(key, "")!!
    }
}