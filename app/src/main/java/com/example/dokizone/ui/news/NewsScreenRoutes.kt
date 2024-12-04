package com.example.dokizone.ui.news

import kotlinx.serialization.Serializable

@Serializable
sealed class NewsScreenRoutes {
    @Serializable
    object NewsScreen: NewsScreenRoutes()
}