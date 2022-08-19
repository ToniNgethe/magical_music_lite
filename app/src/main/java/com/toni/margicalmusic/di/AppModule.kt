package com.toni.margicalmusic.di

import android.content.Context
import com.margicalmusic.core_utils.AppDispatchers
import com.margicalmusic.core_utils.AppDispatchersImpl
import com.margicalmusic.core_database.data.local.AppDataStore
import com.margicalmusic.core_database.data.local.AppDataStoreImpl
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
}