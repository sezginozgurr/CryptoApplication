package com.example.cryto.data.repository

import com.example.base.qualifier.IoDispatcher
import com.example.cryto.data.local.FavoriteDao
import com.example.cryto.data.model.CryptoEntityModel
import com.example.cryto.domain.repository.FavoriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoriteDao: FavoriteDao,
    @IoDispatcher val dispatcher: CoroutineDispatcher
) : FavoriteRepository {

    override suspend fun insertFavorites(cryptoEntityModel: CryptoEntityModel) =
        favoriteDao.insertFavorites(cryptoEntityModel)

    override suspend fun deleteFavorites(cryptoEntityModel: CryptoEntityModel) =
        favoriteDao.deleteFavorites(cryptoEntityModel)

    override fun getAllFavorites(): Flow<List<CryptoEntityModel>> = favoriteDao.getAllFavorites()

}