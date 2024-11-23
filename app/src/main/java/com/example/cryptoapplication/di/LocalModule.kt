package com.example.cryptoapplication.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryptoapplication.data.source.local.CryptoDao
import com.example.cryptoapplication.data.source.local.CryptoRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.databaseBuilder(
            context,
            CryptoRoomDB::class.java,
            CryptoRoomDB::class.simpleName
        ).build()
    }

    @Provides
    fun provideMainDao(database: CryptoRoomDB): CryptoDao = database.mainDao()
}