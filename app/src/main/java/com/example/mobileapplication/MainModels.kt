package com.example.mobileapplication

data class CommentsResponse(val Search:List<Item>,val Response:String,val totalResults:Int)

data class Item(val Title: String,
                val Year:String,
                val Poster:String,val imdbID:String)