package com.example.dokizone.data.remote.anime

import com.example.dokizone.domain.mapper.toAnimeCard
import com.example.dokizone.domain.mapper.toAnimeDetails
import com.example.dokizone.domain.mapper.toEpisode
import com.example.dokizone.domain.mapper.toPromotionalVideoCard
import com.example.dokizone.domain.model.AnimeCard
import com.example.dokizone.domain.model.AnimeDetails
import com.example.dokizone.domain.model.Episode
import com.example.dokizone.domain.model.PromotionalVideoCard
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

    suspend fun getCurrentSeasonAnime(): List<AnimeCard> {
        return animeService.getCurrentSeasonAnime().data.map { it.toAnimeCard() }
    }

    suspend fun getPromotionalVideos(): List<PromotionalVideoCard> {
        return animeService.getPromotionalVideos().data.map { it.toPromotionalVideoCard()  }
    }

    suspend fun getAnimeDetails(animeId: Int): AnimeDetails {
        return animeService.getAnimeDetails(animeId).data.toAnimeDetails()
    }

    suspend fun getEpisodes(animeId: Int): List<Episode> {
        return animeService.getEpisodes(animeId).data.episodes.map { it.toEpisode() }
    }
}