package com.example.cryto.domain.usecase

import com.example.cryto.data.model.CryptoEntityModel
import com.example.cryto.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoDaoUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    suspend fun insertFavorites(cryptoEntityModel: CryptoEntityModel): Unit =
        repository.insertFavorites(cryptoEntityModel)

    suspend fun deleteFavorites(cryptoEntityModel: CryptoEntityModel): Unit =
        repository.deleteFavorites(cryptoEntityModel)

    fun getAllFavorites(): Flow<List<CryptoEntityModel>> = repository.getAllFavorites()
}