package com.khidrew.unittest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khidrew.unittest.model.Post
import com.khidrew.unittest.repo.PostsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PostsViewModel @Inject constructor(private val postsRepo: PostsRepo):ViewModel() {
    private val _posts = MutableLiveData<List<Post>>()
    val posts:LiveData<List<Post>> = _posts

    fun fetchPosts(){
        viewModelScope.launch {
            try {
                val fetchedPosts = postsRepo.getPosts()
                _posts.value = fetchedPosts

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}