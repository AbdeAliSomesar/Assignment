package com.example.koo.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
  companion object {
    val base = "https://gorest.co.in/public/v1/"
    fun create() : APIClient {
      val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(base)
        .build()
      return retrofit.create(APIClient::class.java)
    }
  }
}