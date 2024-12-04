package com.example.dokizone.ui.main

import com.example.dokizone.R
import kotlinx.serialization.Serializable

@Serializable
sealed class TabScreen(
    val title: String,
    val icon: Int
) {
    @Serializable
    object AnimeTab : TabScreen("Anime", R.drawable.ic_launcher_foreground)
    @Serializable
    object MangaTab : TabScreen("Manga", R.drawable.ic_launcher_foreground)
    @Serializable
    object NewsTab : TabScreen("News", R.drawable.ic_launcher_foreground)


    companion object {
        val tabs = listOf(AnimeTab, MangaTab, NewsTab)
    }
}