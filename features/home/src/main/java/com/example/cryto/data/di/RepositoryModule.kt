package com.example.cryto.data.di

import com.example.cryto.data.repository.CryptoRepositoryImpl
import com.example.cryto.data.repository.FavoritesRepositoryImpl
import com.example.cryto.domain.repository.CryptoRepository
import com.example.cryto.domain.repository.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCryptoRepository(
        repositoryImpl: CryptoRepositoryImpl
    ): CryptoRepository

    @Binds
    abstract fun bindMainRepository(repositoryImpl: FavoritesRepositoryImpl): FavoriteRepository
}