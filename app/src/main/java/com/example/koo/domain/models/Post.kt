package com.example.koo.domain.models

import com.google.gson.annotations.SerializedName

data class Post(
  val id:Int,
  @SerializedName("user_id")
  val userID:Int,
  val title:String,
  val body:String)
