package com.example.dokizone.data.remote.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PromoResponse(
    @SerialName("title")
    val title: String,
    @SerialName("entry")
    val entry: EntryResponse?,
    @SerialName("trailer")
    val trailer: Trailer?
)
