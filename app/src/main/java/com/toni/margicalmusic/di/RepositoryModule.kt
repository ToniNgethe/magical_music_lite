package com.toni.margicalmusic.di

import com.toni.margicalmusic.data.repository.*
import com.toni.margicalmusic.domain.repositories.*
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
    abstract fun bindGenereRepository(genreRepositoryImpl: GenreRepositoryImpl): GenreRepository

    @Singleton
    @Binds
    abstract fun bindSongsRepository(songsRepositoryImpl: SongsRepositoryImpl): SongsRepository

    @Singleton
    @Binds
    abstract fun bindLyricRepository(lyricsRepositoryImpl: LyricsRepositoryImpl): LyricsRepository


    @Singleton
    @Binds
    abstract fun bindVideoRepository(videoRepositoryImpl: VideoRepositoryImpl): VideoRepository
}