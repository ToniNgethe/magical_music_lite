package com.margicalmusic.feature_onboarding.di

import com.margicalmusic.feature_onboarding.data.SplashScreenRepoImpl
import com.margicalmusic.feature_onboarding.domain.SplashScreenRepo
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
    abstract fun bindSplashScreenRepo(splashScreenRepoImpl: SplashScreenRepoImpl): SplashScreenRepo
}