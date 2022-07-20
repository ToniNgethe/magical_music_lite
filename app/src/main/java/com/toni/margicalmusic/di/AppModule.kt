package com.toni.margicalmusic.di

import android.content.Context
import com.toni.margicalmusic.data.local.AppDataStore
import com.toni.margicalmusic.data.local.AppDataStoreImpl
import com.toni.margicalmusic.utils.AppDispatchers
import com.toni.margicalmusic.utils.AppDispatchersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideDispatchers(): AppDispatchers = AppDispatchersImpl()

    @Singleton
    @Provides
    fun providesDataStore(@ApplicationContext context: Context): AppDataStore =
        AppDataStoreImpl(context = context)
}