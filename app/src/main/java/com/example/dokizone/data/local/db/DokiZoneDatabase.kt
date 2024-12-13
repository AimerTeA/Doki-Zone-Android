package com.example.dokizone.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dokizone.data.local.db.dao.anime.RandomAnimeDao
import com.example.dokizone.data.local.db.entity.anime.RandomAnimeEntity

@Database(
    entities = [
        RandomAnimeEntity::class
    ],
    version = 1
)
abstract class DokiZoneDatabase : RoomDatabase() {
    abstract fun randomAnimeDao(): RandomAnimeDao
}