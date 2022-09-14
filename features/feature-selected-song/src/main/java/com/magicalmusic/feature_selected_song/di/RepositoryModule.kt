package com.magicalmusic.feature_selected_song.di

import com.magicalmusic.feature_selected_song.data.repository.LyricsRepositoryImpl
import com.magicalmusic.feature_selected_song.data.repository.VideoRepositoryImpl
import com.magicalmusic.feature_selected_song.domain.repository.LyricsRepository
import com.magicalmusic.feature_selected_song.domain.repository.VideoRepository
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
    abstract fun bindLyricRepository(lyricsRepositoryImpl: LyricsRepositoryImpl): LyricsRepository


    @Singleton
    @Binds
    abstract fun bindVideoRepository(videoRepositoryImpl: VideoRepositoryImpl): VideoRepository

}
