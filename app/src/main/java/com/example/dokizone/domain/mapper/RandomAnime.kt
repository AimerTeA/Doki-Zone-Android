package com.example.dokizone.domain.mapper

import com.example.dokizone.data.remote.model.AnimeResponse
import com.example.dokizone.data.remote.model.JikanResponse
import com.example.dokizone.domain.model.RandomAnime

fun JikanResponse<AnimeResponse>.toRandomAnime(): RandomAnime {
    return RandomAnime(
        this.data.images.jpg.largeImageUrl
    )
}

