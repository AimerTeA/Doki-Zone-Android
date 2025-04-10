package com.example.dokizone.data.remote.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EntryResponse(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("url")
    val url: String,
    @SerialName("images")
    val images: Images,
    @SerialName("title")
    val title: String
)