package com.example.koo.domain.repositories.main

import com.example.koo.domain.models.RequestResponse
import com.example.koo.remote.APIEndPoint
import java.lang.Exception
import javax.inject.Inject

class MainRepoImp @Inject constructor(var webService:APIEndPoint): MainRepo {
  override suspend fun getPosts(page: Int): RequestResponse {
    try {
      val response = webService.getPosts(1)
      if(response.data.isNotEmpty())
      {
        return RequestResponse.Success(response.data,response.meta.page)
      }
      return RequestResponse.Error("Page $page not available")
    }catch (e:Exception){
      return RequestResponse.Error("e.msg")
    }
  }
}