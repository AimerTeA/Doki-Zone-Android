package com.example.dokizone.data.remote.anime

import com.example.dokizone.core.Constants
import com.example.dokizone.data.remote.model.anime.AnimeResponse
import com.example.dokizone.data.remote.model.anime.JikanResponse
import com.example.dokizone.data.remote.model.anime.PromoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeService {
    @GET("random/anime")
    suspend fun getRandomAnime(): JikanResponse<AnimeResponse>

    @GET("top/anime")
    suspend fun getMostPopularAnime(
        @Query("filter") filter: String = Constants.Filter.BY_POPULARITY
    ): JikanResponse<List<AnimeResponse>>

    @GET("seasons/now")
    suspend fun getCurrentSeasonAnime(): JikanResponse<List<AnimeResponse>>

    @GET("watch/promos")
    suspend fun getPromotionalVideos(): JikanResponse<List<PromoResponse>>
}