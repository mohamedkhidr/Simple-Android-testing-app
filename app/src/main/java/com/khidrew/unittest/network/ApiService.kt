package com.khidrew.unittest.network

import com.khidrew.unittest.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}