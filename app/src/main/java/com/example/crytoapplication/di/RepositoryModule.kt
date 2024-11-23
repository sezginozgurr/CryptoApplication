package com.example.crytoapplication.di

import com.example.crytoapplication.data.repository.CryptoRepositoryImpl
import com.example.crytoapplication.domain.repository.CryptoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMainRepository(repositoryImpl: CryptoRepositoryImpl): CryptoRepository
}