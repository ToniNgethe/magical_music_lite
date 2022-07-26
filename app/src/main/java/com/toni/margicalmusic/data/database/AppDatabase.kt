package com.toni.margicalmusic.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database( version = 1, entities = [SongsEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao() : SongsDao
}