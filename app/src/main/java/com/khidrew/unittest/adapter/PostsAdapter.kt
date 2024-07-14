package com.khidrew.unittest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khidrew.unittest.R
import com.khidrew.unittest.model.Post

class PostsAdapter(private var posts:List<Post>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val bodyTextView: TextView = itemView.findViewById(R.id.bodyTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostsAdapter.ViewHolder, position: Int) {
        holder.titleTextView.text = posts[position].title
        holder.bodyTextView.text = posts[position].body
    }

    override fun getItemCount(): Int = posts.size


    fun setData(newPosts: List<Post>){
        posts = newPosts
        notifyDataSetChanged()
    }
}