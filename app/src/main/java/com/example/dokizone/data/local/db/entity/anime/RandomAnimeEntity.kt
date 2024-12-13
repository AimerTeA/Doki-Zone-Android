package com.example.dokizone.data.local.db.entity.anime

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RandomAnimeEntity(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "anime_id") val animeId: Int = "random_anime".hashCode(),
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "synopsis") val synopsis: String?
)