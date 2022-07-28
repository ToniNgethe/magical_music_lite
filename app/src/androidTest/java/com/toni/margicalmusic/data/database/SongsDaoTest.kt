package com.toni.margicalmusic.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.toni.margicalmusic.CustomCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SongsDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var songsDao: SongsDao

    @get:Rule
    val coroutineRule = CustomCoroutineRule()

    val songEntity = SongsEntity(
        songId = 1,
        title = "title",
        lyrics = "",
        artistName = "",
        video = "edwsfht"
    )

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        songsDao = database.songDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun shouldInsertSong() = runTest {
        //given
        // when
        songsDao.insert(songEntity)
        // assert
        val songs = songsDao.getSongById(1)
        Truth.assertThat(songs).isNotNull()
        Truth.assertThat(songs).isEqualTo(songEntity)
    }

    @Test
    fun shouldReturnInsertedSong() = runTest {
        val songResponse = songsDao.getSongById(5)
        Truth.assertThat(songResponse).isNull()

        songsDao.insert(songEntity)
        val songs = songsDao.getSongById(songEntity.songId!!)
        Truth.assertThat(songs).isNotNull()
        Truth.assertThat(songs).isEqualTo(songEntity)
    }
}