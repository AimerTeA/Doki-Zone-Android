package com.example.dokizone.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitleResponse(
    @SerialName("type")
    val type: String,
    @SerialName("title")
    val title: String
)
