package com.example.koo.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.koo.databinding.PostListItemBinding
import com.example.koo.domain.models.Post

class MainAdapter (private val onClickListener: PostClickListener, private val loadPosts: LoadNextPosts) :ListAdapter<Post,MainAdapter.ViewHolder>(DIFF_CALLBACK) {
  private val currentPosts = mutableListOf<Post>()
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder.from(parent.context,parent,onClickListener)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    if (position == (itemCount - 3))
      loadPosts.loadPosts()
    holder.bind(getItem(position))
  }

  override fun submitList(list: MutableList<Post>?) {
    list?.let {
      if (it.isNotEmpty()) {
        currentPosts.addAll(it)
        super.submitList(currentPosts)
        notifyItemChanged(currentPosts.lastIndex)
      }
    }
  }

  class ViewHolder(private val binding:PostListItemBinding,private val onClickListener: PostClickListener):RecyclerView.ViewHolder(binding.root){
    fun bind(item:Post){
      binding.title.text = item.title
      binding.description.text = item.body
      binding.cardView.setOnClickListener{
        onClickListener.onClick(item.id)
      }
    }
    companion object{
      fun from(
        context:Context,
        parent: ViewGroup,
        onClickListener: PostClickListener):ViewHolder{
        val binding = PostListItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding, onClickListener)
      }
    }
  }

  companion object{
    val DIFF_CALLBACK: DiffUtil.ItemCallback<Post> =
      object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
          oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
          return oldItem == newItem
        }
      }
  }

  interface PostClickListener {
    fun onClick(postId: Int)
  }

  interface LoadNextPosts {
    fun loadPosts()
  }
}