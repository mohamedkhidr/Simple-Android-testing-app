package com.khidrew.unittest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.khidrew.unittest.model.Post
import com.khidrew.unittest.repo.PostsRepo
import com.khidrew.unittest.viewModel.PostsViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val postsRepo:PostsRepo = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var postsViewModel: PostsViewModel

    @Before
    fun setup(){
        postsViewModel = PostsViewModel(postsRepo)
    }

    @After
    fun tearDown(){
        unmockkAll()
        testDispatcher.cancel()
    }

    @Test
    fun `get posts success`() = testDispatcher.run {
        val posts = listOf(
            Post(1,1,"title1", "body1"),
            Post(2,2,"title2", "body2"),
        )
        coEvery {
            postsRepo.getPosts()
        }returns posts

        val observer:Observer<List<Post>> = mockk(relaxed = true)
        postsViewModel.posts.observeForever(observer)

        postsViewModel.fetchPosts()


        coVerify { postsRepo.getPosts() }
        verify { observer.onChanged(posts) }

        postsViewModel.posts.removeObserver(observer)
    }

    @Test
    fun `fetch posts error`() = testDispatcher.run {
        val exception = Exception("Network error")
        coEvery {
            postsRepo.getPosts()
        }throws exception

        val observer:Observer<List<Post>> = mockk(relaxed = true)
        postsViewModel.posts.observeForever(observer)

        postsViewModel.fetchPosts()


        coVerify { postsRepo.getPosts() }
        postsViewModel.posts.removeObserver(observer)
    }
}