package com.example.dokizone.data.local.db.dao.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dokizone.data.local.db.entity.anime.RandomAnimeEntity

@Dao
interface RandomAnimeDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertRandomAnime(randomAnime: RandomAnimeEntity)

    @Query("SELECT * FROM RandomAnimeEntity ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomAnime(): RandomAnimeEntity?
}