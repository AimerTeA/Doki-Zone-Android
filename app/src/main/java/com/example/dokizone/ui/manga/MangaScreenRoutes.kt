package com.example.dokizone.ui.manga

import kotlinx.serialization.Serializable

@Serializable
sealed class MangaScreenRoutes {
    @Serializable
    object MangaScreen: MangaScreenRoutes()
}