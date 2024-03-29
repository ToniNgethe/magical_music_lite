package com.toni.margicalmusic.di

import com.magicalmusic.songs.domain.SongsRepository
import com.margicalmusic.core_utils.AppDispatchers
import com.margicalmusic.feature_artists.domain.ArtistsRepository
import com.toni.margicalmusic.domain.repositories.GenreRepository
import com.toni.margicalmusic.domain.usecases.GetHomePageDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideGetHomePageDataUseCase(
        genreRepository: GenreRepository,
        songsRepository: SongsRepository,
        artistsRepository: ArtistsRepository,
        appDispatchers: AppDispatchers
    ): GetHomePageDataUseCase = GetHomePageDataUseCase(
        genreSongRepository = genreRepository,
        artistsRepository = artistsRepository,
        songsRepository = songsRepository,
        appDispatchers = appDispatchers
    )
}