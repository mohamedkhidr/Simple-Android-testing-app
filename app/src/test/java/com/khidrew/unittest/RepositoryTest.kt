package com.khidrew.unittest

import com.khidrew.unittest.model.Post
import com.khidrew.unittest.network.ApiService
import com.khidrew.unittest.repo.PostsRepo
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class RepositoryTest {
    private val apiService = mock(ApiService::class.java)
    private lateinit var postsRepo: PostsRepo

    @Before
    fun setup() {
        postsRepo = PostsRepo(apiService)
    }


    @Test
    fun `get posts success`() = runBlocking {
        val posts = listOf(
            Post(1, 1, "Title1", "Body1"),
            Post(2, 2, "Title2", "Body2")
        )
        // state how the mocked object behave
        `when`(apiService.getPosts()).thenReturn(posts)

        // call the function to test
        val result = postsRepo.getPosts()
        verify(apiService).getPosts()
        assert(result == posts)
    }
}