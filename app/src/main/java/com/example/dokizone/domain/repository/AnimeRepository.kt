package com.example.dokizone.domain.repository

import com.example.dokizone.core.Result
import com.example.dokizone.core.safeCall
import com.example.dokizone.data.local.anime.AnimeLocalDataSource
import com.example.dokizone.data.local.time.TimeLocalDataSource
import com.example.dokizone.data.remote.anime.AnimeRemoteDataSource
import com.example.dokizone.domain.model.AnimeCard
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AnimeRepository @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val animeRemoteDataSource: AnimeRemoteDataSource,
    private val animeLocalDataSource: AnimeLocalDataSource,
    private val timeLocalDataSource: TimeLocalDataSource
) {
    suspend fun getRandomAnime(): Result<AnimeCard> {
        return coroutineDispatcher.safeCall {
            val anime = animeLocalDataSource.getRandomAnime()
            if (anime != null && !timeLocalDataSource.shouldUpdateRandomAnime()) {
                return@safeCall anime
            }

            val animeFromRemote = animeRemoteDataSource.getRandomAnime()
            animeLocalDataSource.insertRandomAnime(animeFromRemote)
            timeLocalDataSource.updateTimeRandomAnimeSaved()
            animeFromRemote
        }
    }
}