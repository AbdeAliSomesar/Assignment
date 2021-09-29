package com.example.koo.remote

import com.example.koo.domain.models.PostAPIResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIEndPoint {
  @GET("posts")
  suspend fun getPosts(@Query("page") page:Int):PostAPIResponse
}