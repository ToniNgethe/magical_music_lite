package com.toni.margicalmusic.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SongsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(song: SongsEntity?)

    @Query("SELECT * FROM song WHERE songId=:songId")
    suspend fun getSongById(songId: Int): SongsEntity?
}