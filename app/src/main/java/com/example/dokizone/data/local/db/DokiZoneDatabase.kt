package com.example.dokizone.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dokizone.data.local.db.dao.anime.AnimeDao
import com.example.dokizone.data.local.db.dao.home_section.HomeSectionDao
import com.example.dokizone.data.local.db.entity.anime.AnimeCardEntity
import com.example.dokizone.data.local.db.entity.anime.AnimeCardHomeSectionCrossRef
import com.example.dokizone.data.local.db.entity.home_section.HomeSectionEntity
import com.example.dokizone.data.local.db.type_converter.home_section.HomeSectionTypeConverter

@Database(
    entities = [
        AnimeCardEntity::class,
        HomeSectionEntity::class,
        AnimeCardHomeSectionCrossRef::class
    ],
    version = 2
)
@TypeConverters(HomeSectionTypeConverter::class)
abstract class DokiZoneDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao

    abstract fun homeSectionDao(): HomeSectionDao
}