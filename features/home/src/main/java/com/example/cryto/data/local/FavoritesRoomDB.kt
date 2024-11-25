package com.example.cryto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryto.data.model.CryptoEntityModel

@Database(entities = [CryptoEntityModel::class], version = 1, exportSchema = false)
abstract class FavoritesRoomDB : RoomDatabase() {

    abstract fun favoritesDao(): FavoriteDao
}