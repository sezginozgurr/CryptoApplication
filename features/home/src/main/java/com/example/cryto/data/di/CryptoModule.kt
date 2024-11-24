package com.example.cryto.data.di

import com.example.cryto.data.repository.CryptoRepositoryImpl
import com.example.cryto.domain.repository.CryptoRepository
import com.example.cryto.domain.usecase.GetCryptoUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoModule {

    @Binds
    abstract fun bindCryptoRepository(
        repositoryImpl: CryptoRepositoryImpl
    ): CryptoRepository
}