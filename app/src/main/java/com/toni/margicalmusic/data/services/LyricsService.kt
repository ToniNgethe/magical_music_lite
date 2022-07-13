package com.toni.margicalmusic.data.services

import com.toni.margicalmusic.data.dto.lyrics.LyricsRequestDto
import com.toni.margicalmusic.data.dto.lyrics.LyricsResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LyricsService {

    @POST("lyrics")
    suspend fun getLyrics(@Body request: LyricsRequestDto): LyricsResponseDto
}