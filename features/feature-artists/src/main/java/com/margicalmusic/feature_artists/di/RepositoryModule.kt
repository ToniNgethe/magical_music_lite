package com.margicalmusic.feature_artists.di

import com.margicalmusic.feature_artists.data.repository.ArtistsRepositoryImpl
import com.margicalmusic.feature_artists.domain.ArtistsRepository
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
    abstract fun bindArtistrepository(artistsRepositoryImpl: ArtistsRepositoryImpl): ArtistsRepository
}