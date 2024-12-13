package com.example.dokizone.data.remote.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JikanResponse<T>(
    @SerialName("pagination")
    val pagination: Pagination? = null,
    @SerialName("data")
    val data: T
)
