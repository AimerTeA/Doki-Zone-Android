package com.example.dokizone.data.local.db.dao.anime

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.dokizone.data.local.db.entity.anime.AnimeCardEntity
import com.example.dokizone.data.local.db.entity.anime.AnimeCardHomeSectionCrossRef
import com.example.dokizone.data.local.db.entity.anime.HomeSectionWithAnimeCards

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeCards(animeCards: List<AnimeCardEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimeCardHomeSectionCrossRef(crossRef: List<AnimeCardHomeSectionCrossRef>)

    @Transaction
    @Query("SELECT * from home_sections WHERE section_name = :section")
    suspend fun getAnimeCardsByHomeSection(section: String): List<HomeSectionWithAnimeCards>
}