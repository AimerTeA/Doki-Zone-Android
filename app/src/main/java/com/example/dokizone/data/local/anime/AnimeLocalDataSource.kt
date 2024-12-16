package com.example.dokizone.data.local.anime

import com.example.dokizone.core.Constants
import com.example.dokizone.data.local.db.dao.anime.AnimeDao
import com.example.dokizone.data.local.db.entity.anime.AnimeCardHomeSectionCrossRef
import com.example.dokizone.domain.mapper.toAnimeCard
import com.example.dokizone.domain.mapper.toAnimeEntity
import com.example.dokizone.domain.model.AnimeCard
import javax.inject.Inject

class AnimeLocalDataSource @Inject constructor(
    private val animeDao: AnimeDao
) {
    suspend fun insertAnimeCardsWithHomeSection(section: Constants.HomeSection, animeCards: List<AnimeCard>) {
        animeDao.insertAnimeCards(
            animeCards.map { it.toAnimeEntity() }
        )

        val animeCrossRef = animeCards.map {
            AnimeCardHomeSectionCrossRef(
                animeCardId = it.id,
                homeSectionName = section.name
            )
        }
        animeDao.insertAnimeCardHomeSectionCrossRef(animeCrossRef)
    }

    suspend fun getAnimeAnimeCardsByHomeSection(section: Constants.HomeSection): List<AnimeCard> {
        return animeDao.getAnimeCardsByHomeSection(section.name).firstOrNull()?.animeCards?.map { it.toAnimeCard() }.orEmpty()
    }
}