package com.toni.margicalmusic.di

import com.toni.margicalmusic.data.repository.ArtistsRepositoryImpl
import com.toni.margicalmusic.data.repository.GenreRepositoryImpl
import com.toni.margicalmusic.data.repository.SongsRepositoryImpl
import com.toni.margicalmusic.domain.repositories.ArtistsRepository
import com.toni.margicalmusic.domain.repositories.GenreRepository
import com.toni.margicalmusic.domain.repositories.SongsRepository
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
    abstract fun bindArtistrepository(artistsRepositoryImpl: ArtistsRepositoryImpl): ArtistsRepository
}