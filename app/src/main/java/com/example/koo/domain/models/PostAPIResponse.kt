package com.example.koo.domain.models

data class PostAPIResponse(
  val meta:Pagination,
  val data:List<Post>
  )
