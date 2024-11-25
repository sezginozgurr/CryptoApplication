package com.example.cryto.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryto.data.model.CryptoEntityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(cryptoEntityModel: CryptoEntityModel): Unit

    @Delete
    suspend fun deleteFavorites(cryptoEntityModel: CryptoEntityModel)

    @Query("SELECT * FROM cryptoentitymodel")
    fun getAllFavorites(): Flow<List<CryptoEntityModel>>

}