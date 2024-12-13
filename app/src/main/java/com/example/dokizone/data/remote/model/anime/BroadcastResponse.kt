package com.example.dokizone.data.remote.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BroadcastResponse(
    @SerialName("day")
    val day: String?,
    @SerialName("time")
    val time: String?,
    @SerialName("timezone")
    val timezone: String?,
    @SerialName("string")
    val string: String?
)
