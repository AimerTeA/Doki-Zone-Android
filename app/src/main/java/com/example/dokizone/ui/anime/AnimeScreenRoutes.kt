package com.example.dokizone.ui.anime

import kotlinx.serialization.Serializable

@Serializable
sealed class AnimeScreenRoutes {
    @Serializable
    object AnimeScreen: AnimeScreenRoutes()
}