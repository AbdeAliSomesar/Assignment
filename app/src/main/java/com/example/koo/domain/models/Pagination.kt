package com.example.koo.domain.models

data class Pagination(
  val total:Int,
  val pages:Int,
  val page:Int,
  val limit:Int,
)
