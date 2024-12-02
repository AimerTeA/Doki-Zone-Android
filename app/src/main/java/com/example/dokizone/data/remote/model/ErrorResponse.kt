package com.example.dokizone.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("type")
    val type: String,
    @SerialName("message")
    val message: String? = null,
    @SerialName("messages")
    val messages: MessageError? = null,
    @SerialName("error")
    val error: String?
)

@Serializable
data class MessageError(
    @SerialName("id")
    val id: List<String>
)