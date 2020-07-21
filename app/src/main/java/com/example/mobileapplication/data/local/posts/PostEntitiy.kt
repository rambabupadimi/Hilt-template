package com.example.mobileapplication.data.local.posts

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
class PostEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,
    @NonNull
    @ColumnInfo(name = "name")
    val name: String,
    @NonNull
    @ColumnInfo(name = "message")
    val message: String
)