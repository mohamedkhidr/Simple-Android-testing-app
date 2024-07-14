package com.khidrew.unittest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.khidrew.unittest.adapter.PostsAdapter
import com.khidrew.unittest.databinding.ActivityMainBinding
import com.khidrew.unittest.viewModel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var postsAdapter: PostsAdapter
    private val postsViewModel: PostsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupList();


    }

    private fun setupList() {
        binding.rv.layoutManager = LinearLayoutManager(this)
        postsAdapter = PostsAdapter(emptyList())
        binding.rv.adapter = postsAdapter
        postsViewModel.posts.observe(this){posts ->
            postsAdapter.setData(posts)
        }

        lifecycleScope.launch {
            try {
                postsViewModel.fetchPosts()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }


    }
}