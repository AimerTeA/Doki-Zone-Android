package com.example.dokizone.domain.mapper

import com.example.dokizone.data.local.db.entity.anime.RandomAnimeEntity
import com.example.dokizone.data.remote.model.anime.AnimeResponse
import com.example.dokizone.data.remote.model.anime.JikanResponse
import com.example.dokizone.domain.model.AnimeCard

fun JikanResponse<AnimeResponse>.toAnimeCard(): AnimeCard {
    val data = this.data
    return AnimeCard(
        id = data.malId,
        imageUrl = data.images.jpg.largeImageUrl,
        title = data.title,
        synopsis = data.synopsis
    )
}

fun RandomAnimeEntity.toAnimeCard(): AnimeCard {
    return AnimeCard(
        id = this.animeId,
        imageUrl = this.imageUrl,
        title = this.title,
        synopsis = this.synopsis
    )
}

fun AnimeCard.toRandomAnimeEntity(): RandomAnimeEntity {
    return RandomAnimeEntity(
        animeId = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        synopsis = this.synopsis
    )
}

