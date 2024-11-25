package com.example.cryto.domain.repository

import com.example.cryto.data.model.CryptoEntityModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun insertFavorites(cryptoEntityModel: CryptoEntityModel)

    suspend fun deleteFavorites(cryptoEntityModel: CryptoEntityModel)

    fun getAllFavorites(): Flow<List<CryptoEntityModel>>
}