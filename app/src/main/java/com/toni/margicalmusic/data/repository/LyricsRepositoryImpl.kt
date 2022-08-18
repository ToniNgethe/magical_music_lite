package com.toni.margicalmusic.data.repository
import com.margicalmusic.core_network.ErrorHandler.parseRequestException
import com.margicalmusic.core_network.ResponseState
import com.margicalmusic.core_network.services.LyricsService
import com.margicalmusic.core_utils.AppDispatchers
import com.margicalmusic.core_utils.UiText
import com.margicalmusic.core_network.dto.lyrics.LyricsRequestDto
import com.toni.margicalmusic.data.mappers.toLyricModel
import com.toni.margicalmusic.domain.models.Lyric
import com.toni.margicalmusic.domain.repositories.LyricsRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LyricsRepositoryImpl @Inject constructor(
    private val lyricsService: LyricsService, val appDispatchers: AppDispatchers
) : LyricsRepository {

    override suspend fun fetchLyrics(title: String, artistName: String): ResponseState<Lyric> =
        withContext(appDispatchers.io()) {
            try {
                val response = lyricsService.getLyrics(
                    LyricsRequestDto(
                        artist = artistName, song = title
                    )
                )
                if (response.status == "00") {
                    ResponseState.Success(response.toLyricModel())
                } else {
                    ResponseState.Error(UiText.DynamicText(response.message!!))
                }

            } catch (e: Exception) {
                parseRequestException(e)
            }
        }
}