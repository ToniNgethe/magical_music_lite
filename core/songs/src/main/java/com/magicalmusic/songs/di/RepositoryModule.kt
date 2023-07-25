package com.magicalmusic.feature_song.di

import com.magicalmusic.songs.data.SongsRepositoryImpl
import com.magicalmusic.songs.domain.SongsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindSongsRepository(songsRepositoryImpl: com.magicalmusic.songs.data.SongsRepositoryImpl): com.magicalmusic.songs.domain.SongsRepository
}