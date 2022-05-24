package com.toni.margicalmusic.di

import android.content.Context
import com.toni.margicalmusic.data.dataloaders.AlbumLoader
import com.toni.margicalmusic.data.dataloaders.ArtistLoader
import com.toni.margicalmusic.data.dataloaders.GenreLoader
import com.toni.margicalmusic.data.dataloaders.SongLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MediaModule {

    @Provides
    @Singleton
    fun provideAlbums(@ApplicationContext context: Context): AlbumLoader = AlbumLoader(context)

    @Provides
    @Singleton
    fun provideGenres(@ApplicationContext context: Context, songLoader: SongLoader): GenreLoader = GenreLoader(context, songLoader)

    @Provides
    @Singleton
    fun provideArtists(@ApplicationContext context: Context): ArtistLoader = ArtistLoader(context)

    @Provides
    @Singleton
    fun provideSongsLoader(@ApplicationContext context: Context): SongLoader = SongLoader(context)
}