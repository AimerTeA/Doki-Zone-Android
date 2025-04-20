package com.example.dokizone.data.remote.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("title")
    val title: String,
    @SerialName("episode")
    val episode: String,
    @SerialName("url")
    val url: String,
    @SerialName("images")
    val images: Images
)


