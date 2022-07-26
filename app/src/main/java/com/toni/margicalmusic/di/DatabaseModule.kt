package com.toni.margicalmusic.di

import android.content.Context
import androidx.room.Room
import com.toni.margicalmusic.data.database.AppDatabase
import com.toni.margicalmusic.data.database.SongsDao
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Singleton
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, ""
    ).build()

    @Provides
    fun provideSingDao(appDatabase: AppDatabase): SongsDao = appDatabase.songDao()
}