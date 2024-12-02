package com.example.dokizone.domain.repository

import com.example.dokizone.core.Result
import com.example.dokizone.core.safeCall
import com.example.dokizone.data.remote.anime.AnimeRemoteDataSource
import com.example.dokizone.domain.model.RandomAnime
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val animeRemoteDataSource: AnimeRemoteDataSource
) {
    suspend fun getRandomAnime(): Result<RandomAnime> {
        return coroutineDispatcher.safeCall{
            animeRemoteDataSource.getRandomAnime()
        }
    }
}