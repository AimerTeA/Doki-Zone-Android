package com.example.dokizone.data.remote.model.anime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aired(
    @SerialName("from")
    val from: String?,
    @SerialName("to")
    val to: String?,
    @SerialName("prop")
    val prop: Prop?,
    @SerialName("string")
    val string: String?
)

@Serializable
data class Prop(
    @SerialName("from")
    val from: From?,
    @SerialName("to")
    val to: To?
)

@Serializable
data class From(
    @SerialName("day")
    val day: Int?,
    @SerialName("month")
    val month: Int?,
    @SerialName("year")
    val year: Int?
)

@Serializable
data class To(
    @SerialName("day")
    val day: Int?,
    @SerialName("month")
    val month: Int?,
    @SerialName("year")
    val year: Int?
)
