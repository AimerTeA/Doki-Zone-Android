package com.example.dokizone.data.local.db.entity.anime

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_card")
data class AnimeCardEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "synopsis")
    val synopsis: String?,
    @ColumnInfo(name = "video_url")
    val videoUrl: String?
)