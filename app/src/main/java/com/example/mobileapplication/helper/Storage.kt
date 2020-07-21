package com.example.mobileapplication.helper

interface Storage {

    fun getString(key: String, value : String)
    fun getString(key: String) : String
}