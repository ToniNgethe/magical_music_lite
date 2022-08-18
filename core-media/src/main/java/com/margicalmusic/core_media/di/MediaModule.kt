package com.margicalmusic.core_media.di

import android.content.Context
import com.margicalmusic.core_media.dataloaders.AlbumLoader
import com.margicalmusic.core_media.dataloaders.ArtistLoader
import com.margicalmusic.core_media.dataloaders.GenreLoader
import com.margicalmusic.core_media.dataloaders.SongLoader
import com.margicalmusic.core_utils.AppDispatchers
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
    fun provideGenres(
        @ApplicationContext context: Context, songLoader: SongLoader, appDispatchers: AppDispatchers
    ): GenreLoader = GenreLoader(context, songLoader, appDispatchers = appDispatchers)

    @Provides
    @Singleton
    fun provideArtists(@ApplicationContext context: Context): ArtistLoader = ArtistLoader(context)

    @Provides
    @Singleton
    fun provideSongsLoader(@ApplicationContext context: Context): SongLoader = SongLoader(context)
}