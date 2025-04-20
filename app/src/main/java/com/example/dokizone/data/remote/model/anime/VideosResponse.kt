package com.example.dokizone.data.remote.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosResponse(
    @SerialName("promo")
    val promo: List<PromoResponse>,
    @SerialName("episodes")
    val episodes: List<EpisodeResponse>
)