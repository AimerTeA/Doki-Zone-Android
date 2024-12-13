package com.example.dokizone.data.local.anime

import com.example.dokizone.data.local.db.dao.anime.RandomAnimeDao
import com.example.dokizone.domain.mapper.toAnimeCard
import com.example.dokizone.domain.mapper.toRandomAnimeEntity
import com.example.dokizone.domain.model.AnimeCard
import javax.inject.Inject

class AnimeLocalDataSource @Inject constructor(
    private val randomAnimeDao: RandomAnimeDao
) {
    suspend fun insertRandomAnime(animeCard: AnimeCard) {
        randomAnimeDao.insertRandomAnime(animeCard.toRandomAnimeEntity())
    }

    suspend fun getRandomAnime(): AnimeCard? {
        return randomAnimeDao.getRandomAnime()?.toAnimeCard()
    }
}