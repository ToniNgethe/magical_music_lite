package com.margicalmusic.core_database.di

import android.content.Context
import androidx.room.Room
import com.margicalmusic.core_database.dao.SongsDao
import com.margicalmusic.core_database.database.AppDatabase
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
}