package com.khidrew.unittest.repo

import com.khidrew.unittest.model.Post
import com.khidrew.unittest.network.ApiService
import javax.inject.Inject

class PostsRepo @Inject constructor(private val apiService: ApiService){
    suspend fun getPosts():List<Post>{
        return apiService.getPosts()
    }
}