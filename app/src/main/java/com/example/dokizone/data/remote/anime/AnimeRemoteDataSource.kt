package com.example.dokizone.data.remote.anime

import com.example.dokizone.domain.model.RandomAnime
import com.example.dokizone.domain.mapper.toRandomAnime
import javax.inject.Inject

class AnimeRemoteDataSource @Inject constructor(
    private val animeService: AnimeService
) {
    suspend fun getRandomAnime(): RandomAnime {
        return animeService.getRandomAnime().toRandomAnime()
    }
}