package com.example.dokizone.domain.mapper

import com.example.dokizone.data.local.db.entity.anime.AnimeCardEntity
import com.example.dokizone.data.remote.model.anime.AnimeResponse
import com.example.dokizone.data.remote.model.anime.JikanResponse
import com.example.dokizone.domain.model.AnimeCard

fun JikanResponse<AnimeResponse>.toAnimeCard(): AnimeCard {
    return this.data.toAnimeCard()
}

fun AnimeResponse.toAnimeCard(): AnimeCard {
    return AnimeCard(
        id = malId,
        imageUrl = images.jpg.largeImageUrl,
        title = title,
        synopsis = synopsis
    )
}

fun AnimeCardEntity.toAnimeCard(): AnimeCard {
    return AnimeCard(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        synopsis = this.synopsis
    )
}

fun AnimeCard.toAnimeEntity(): AnimeCardEntity {
    return AnimeCardEntity(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        synopsis = this.synopsis,
        videoUrl = null
    )
}
