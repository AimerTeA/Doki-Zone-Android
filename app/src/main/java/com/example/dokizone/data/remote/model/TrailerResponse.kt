package com.example.dokizone.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    @SerialName("youtube_id")
    val youtubeId: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("embed_url")
    val embedUrl: String?,
    @SerialName("images")
    val images: TrailerImages?
)

@Serializable
data class TrailerImages(
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("small_image_url")
    val smallImageUrl: String?,
    @SerialName("medium_image_url")
    val mediumImageUrl: String?,
    @SerialName("large_image_url")
    val largeImageUrl: String?,
    @SerialName("maximum_image_url")
    val maximumImageUrl: String?
)
