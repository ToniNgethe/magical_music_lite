package com.toni.margicalmusic.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SongsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(song: SongsEntity)

    @Query("SELECT * FROM songs WHERE songId=:songId")
    suspend fun getSongBuyId(songId: String): SongsEntity
}