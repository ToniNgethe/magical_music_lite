package com.toni.margicalmusic.di

import com.toni.margicalmusic.domain.repositories.ArtistsRepository
import com.toni.margicalmusic.domain.repositories.GenreRepository
import com.toni.margicalmusic.domain.repositories.SongsRepository
import com.toni.margicalmusic.domain.usecases.GetHomePageDataUseCase
import com.toni.margicalmusic.utils.AppDispatchers
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