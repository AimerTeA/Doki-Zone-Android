package com.example.dokizone.data.remote.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudioResponse(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("type")
    val type: String,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
