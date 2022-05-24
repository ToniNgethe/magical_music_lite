package com.toni.margicalmusic.di

import com.toni.margicalmusic.utils.AppDispatchers
import com.toni.margicalmusic.utils.AppDispatchersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideDispatchers() : AppDispatchers = AppDispatchersImpl()

}