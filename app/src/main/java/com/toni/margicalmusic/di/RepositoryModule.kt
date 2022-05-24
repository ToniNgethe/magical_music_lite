package com.toni.margicalmusic.di

import com.toni.margicalmusic.data.dataloaders.ArtistLoader
import com.toni.margicalmusic.data.dataloaders.GenreLoader
import com.toni.margicalmusic.data.dataloaders.SongLoader
import com.toni.margicalmusic.data.repository.ArtistsRepositoryImpl
import com.toni.margicalmusic.data.repository.GenreRepositoryImpl
import com.toni.margicalmusic.data.repository.SongsRepositoryImpl
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

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGenreRepository(
        genreLoader: GenreLoader, appDispatchers: AppDispatchers
    ): GenreRepository = GenreRepositoryImpl(
        genreLoader = genreLoader, appDispatchers = appDispatchers
    )

    @Provides
    @Singleton
    fun provideSongsRepository(
        songLoader: SongLoader, appDispatchers: AppDispatchers
    ): SongsRepository = SongsRepositoryImpl(
        songLoader = songLoader, appDispatchers = appDispatchers
    )

    @Provides
    @Singleton
    fun provideArtistsRepository(
        artistLoader: ArtistLoader, appDispatchers: AppDispatchers
    ): ArtistsRepository = ArtistsRepositoryImpl(
        artistsLoader = artistLoader, appDispatchers = appDispatchers
    )

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