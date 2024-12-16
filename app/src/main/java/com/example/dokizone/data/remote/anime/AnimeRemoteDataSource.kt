package com.example.dokizone.data.remote.anime

import com.example.dokizone.domain.model.AnimeCard
import com.example.dokizone.domain.mapper.toAnimeCard
import javax.inject.Inject

class AnimeRemoteDataSource @Inject constructor(
    private val animeService: AnimeService
) {
    suspend fun getRandomAnime(): AnimeCard {
        return animeService.getRandomAnime().toAnimeCard()
    }

    suspend fun getMostPopularAnime(): List<AnimeCard> {
        return animeService.getMostPopularAnime().data.map { it.toAnimeCard() }
    }
}