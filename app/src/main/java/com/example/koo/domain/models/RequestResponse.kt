package com.example.koo.domain.models

sealed class RequestResponse {
  data class Error(val msg:String) : RequestResponse()
  data class Success(val posts: List<Post>, val page:Int) :RequestResponse()
}