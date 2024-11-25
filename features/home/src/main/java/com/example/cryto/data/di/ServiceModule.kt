package com.example.cryto.data.di

import com.example.cryto.data.service.CryptoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideCryptoService(retrofit: Retrofit): CryptoService {
        return retrofit.create(CryptoService::class.java)
    }
}