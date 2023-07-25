package com.margicalmusic.core_network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.margicalmusic.core_network.BuildConfig
import com.margicalmusic.core_network.services.LyricsService
import com.margicalmusic.core_network.services.VideosService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
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
    fun provideJson() = Json {
        ignoreUnknownKeys = true  // find out why MissingFieldException is still being thrown when this flag is true
        isLenient = true
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder().client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    fun provideLyricsService(retrofit: Retrofit): LyricsService =
        retrofit.create(LyricsService::class.java)

    @Provides
    @Singleton
    fun providesVideoService(retrofit: Retrofit): VideosService =
        retrofit.create(VideosService::class.java)

}