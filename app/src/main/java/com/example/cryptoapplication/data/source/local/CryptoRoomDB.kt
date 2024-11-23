package com.example.cryptoapplication.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptoapplication.data.model.CryptoEntityModel

@Database(entities = [CryptoEntityModel::class], version = 1, exportSchema = false)
abstract class CryptoRoomDB : RoomDatabase() {
    abstract fun mainDao(): CryptoDao
}