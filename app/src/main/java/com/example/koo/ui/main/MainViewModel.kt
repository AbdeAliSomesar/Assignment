package com.example.koo.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koo.domain.models.Post
import com.example.koo.domain.models.RequestResponse
import com.example.koo.domain.repositories.main.MainRepo
import com.example.koo.util.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext var context: Context, var repo:MainRepo):ViewModel() {
  val posts = MutableLiveData<List<Post>>(mutableListOf())
  private var currentPage = 0
  private val ioScope = Dispatchers.IO
init {
  getNextPosts()
}
  fun getNextPosts(){
    viewModelScope.launch(ioScope) {
      val res = repo.getPosts(currentPage + 1)
      withContext(Dispatchers.Main) {
        when (res) {
          is RequestResponse.Error ->
            context.toast("Page ${currentPage + 1} is not available")
          is RequestResponse.Success -> {
            posts.value = res.posts
            currentPage++
          }
        }
      }
    }
  }
}