package com.margicalmusic.core_database.di

import android.content.Context
import androidx.room.Room
import com.margicalmusic.core_database.data.dao.SongsDao
import com.margicalmusic.core_database.data.database.AppDatabase
import com.margicalmusic.core_database.data.local.AppDataStore
import com.margicalmusic.core_database.data.local.AppDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "app_database"
    ).build()

    @Provides
    @Singleton
    fun provideSingDao(appDatabase: AppDatabase): SongsDao = appDatabase.songDao()


    @Singleton
    @Provides
    fun providesDataStore(@ApplicationContext context: Context): AppDataStore =
        AppDataStoreImpl(context = context)
}