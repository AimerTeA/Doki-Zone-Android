package com.example.dokizone.domain.repository

import com.example.dokizone.core.Constants.HomeSection
import com.example.dokizone.core.Result
import com.example.dokizone.core.network.NetworkManager
import com.example.dokizone.core.safeCall
import com.example.dokizone.core.safeCallWithNetworkCheck
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
    private val timeLocalDataSource: TimeLocalDataSource,
    private val networkManager: NetworkManager
) {
    suspend fun getRandomAnime(): Result<AnimeCard> {
        return coroutineDispatcher.safeCall {
            val section = HomeSection.RANDOM
            val anime = animeLocalDataSource.getAnimeAnimeCardsByHomeSection(section).firstOrNull()
            if (anime != null && !timeLocalDataSource.shouldUpdateRandomAnime()) {
                return@safeCall anime
            }

            val animeFromRemote = animeRemoteDataSource.getRandomAnime()
            animeLocalDataSource.insertAnimeCardsWithHomeSection(
                section = section,
                animeCards = listOf(animeFromRemote)
            )
            timeLocalDataSource.updateTimeRandomAnimeSaved()
            animeFromRemote
        }
    }

    suspend fun getMostPopularAnime(): Result<List<AnimeCard>> {
        return coroutineDispatcher.safeCallWithNetworkCheck(
            networkManager = networkManager,
            remoteCall = { animeRemoteDataSource.getMostPopularAnime().take(10) },
            localCall = {
                animeLocalDataSource.getAnimeAnimeCardsByHomeSection(
                    section = HomeSection.POPULAR
                )
                        },
            saveToLocalCall = {
                animeLocalDataSource.insertAnimeCardsWithHomeSection(
                    section = HomeSection.POPULAR,
                    animeCards = it
                )
            }
        )
    }
}