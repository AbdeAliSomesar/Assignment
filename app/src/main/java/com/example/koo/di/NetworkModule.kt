package com.example.koo.di

import com.example.koo.remote.APIEndPoint
import com.example.koo.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

//  @Singleton
//  @Provides
//  fun provideOkHttpClient(basicAuthInterceptor: Interceptor): OkHttpClient {
//    val builder = OkHttpClient.Builder()
//    return builder.addInterceptor(basicAuthInterceptor).build()
//  }

  @Singleton
  @Provides
  fun provideEndpoint(
  ): APIEndPoint =
    Retrofit.Builder()
      .baseUrl(Constants.baseURL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(APIEndPoint::class.java)
}