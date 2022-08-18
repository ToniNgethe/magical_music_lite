package com.margicalmusic.core_network.services

import com.margicalmusic.core_network.dto.lyrics.LyricsRequestDto
import com.margicalmusic.core_network.dto.lyrics.LyricsResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LyricsService {

    @POST("lyrics")
    suspend fun getLyrics(@Body request: LyricsRequestDto): LyricsResponseDto
}