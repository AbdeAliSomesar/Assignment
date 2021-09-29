package com.example.koo.di

import com.example.koo.domain.repositories.main.MainRepo
import com.example.koo.domain.repositories.main.MainRepoImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Binds
  @Singleton
  abstract fun bindMainRepo(mainRepoImp: MainRepoImp):MainRepo
}