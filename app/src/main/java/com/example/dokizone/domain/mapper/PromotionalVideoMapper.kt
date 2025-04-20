package com.example.dokizone.domain.mapper

import com.example.dokizone.data.local.db.entity.anime.AnimeCardEntity
import com.example.dokizone.data.remote.model.anime.PromoResponse
import com.example.dokizone.domain.model.PromotionalVideoCard

fun PromoResponse.toPromotionalVideoCard(): PromotionalVideoCard {
    return PromotionalVideoCard(
        id = entry!!.malId,
        title = "${entry.title} - $title",
        imageUrl = entry.images.jpg.largeImageUrl!!,
        videoUrl = trailer?.youtubeId.orEmpty()
    )
}

fun PromotionalVideoCard.toAnimeEntity(): AnimeCardEntity {
    return AnimeCardEntity(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        synopsis = String(),
        videoUrl = videoUrl
    )
}

fun AnimeCardEntity.toPromotionalVideoCard(): PromotionalVideoCard {
    return PromotionalVideoCard(
        id = this.id,
        title = this.title,
        imageUrl = this.imageUrl,
        videoUrl = this.videoUrl.orEmpty()
    )
}