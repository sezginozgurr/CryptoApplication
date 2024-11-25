package com.example.cryto.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cryto.data.local.FavoriteDao
import com.example.cryto.data.local.FavoritesRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): FavoritesRoomDB {
        return Room.databaseBuilder(
            context,
            FavoritesRoomDB::class.java,
            FavoritesRoomDB::class.simpleName
        ).build()
    }

    @Provides
    fun provideMainDao(database: FavoritesRoomDB): FavoriteDao = database.favoritesDao()
}