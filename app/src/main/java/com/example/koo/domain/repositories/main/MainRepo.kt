package com.example.koo.domain.repositories.main

import com.example.koo.domain.models.Post
import com.example.koo.domain.models.RequestResponse

interface MainRepo {
  suspend fun getPosts(page:Int): RequestResponse
}