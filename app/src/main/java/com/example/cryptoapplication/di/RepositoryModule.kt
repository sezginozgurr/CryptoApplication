package com.example.cryptoapplication.di

import com.example.cryptoapplication.data.repository.CryptoRepositoryImpl
import com.example.cryptoapplication.domain.repository.CryptoRepository
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