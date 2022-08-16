package com.margicalmusic.core_database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.margicalmusic.core_database.dao.SongsDao
import com.margicalmusic.core_database.entity.SongsEntity

@Database( version = 1, entities = [SongsEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao() : SongsDao
}