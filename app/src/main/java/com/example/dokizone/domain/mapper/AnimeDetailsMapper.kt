package com.example.dokizone.domain.mapper

import com.example.dokizone.data.remote.model.anime.AnimeResponse
import com.example.dokizone.domain.model.AnimeDetails

fun AnimeResponse.toAnimeDetails(): AnimeDetails {
    return AnimeDetails(
        id = malId,
        imageUrl = images.jpg.largeImageUrl!!,
        title = title,
        synopsis = synopsis.orEmpty()
    )
}