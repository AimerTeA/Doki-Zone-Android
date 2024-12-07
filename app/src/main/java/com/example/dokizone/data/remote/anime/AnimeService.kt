package com.example.dokizone.data.remote.anime

import com.example.dokizone.data.remote.model.AnimeResponse
import com.example.dokizone.data.remote.model.JikanResponse
import retrofit2.http.GET

interface AnimeService {
    @GET("random/anime")
    suspend fun getRandomAnime(): JikanResponse<AnimeResponse>
}