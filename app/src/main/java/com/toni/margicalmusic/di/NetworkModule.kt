package com.toni.margicalmusic.di

import com.toni.margicalmusic.BuildConfig
import com.toni.margicalmusic.data.services.LyricsService
import com.toni.margicalmusic.data.services.VideosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).build()

    @Provides
    @Singleton
    fun provideLyricsService(retrofit: Retrofit): LyricsService =
        retrofit.create(LyricsService::class.java)

    @Provides
    @Singleton
    fun providesVideoService(retrofit: Retrofit): VideosService =
        retrofit.create(VideosService::class.java)

}