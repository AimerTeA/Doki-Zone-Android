package com.example.dokizone.ui.main

import com.example.dokizone.R
import kotlinx.serialization.Serializable

@Serializable
sealed class TabScreen(
    val title: String,
    val icon: Int
) {
    @Serializable
    object AnimeTab : TabScreen("Anime", R.drawable.ic_movie)
    @Serializable
    object MangaTab : TabScreen("Manga", R.drawable.ic_paper)
    @Serializable
    object NewsTab : TabScreen("News", R.drawable.ic_news)


    companion object {
        val tabs = listOf(AnimeTab, MangaTab, NewsTab)
    }
}