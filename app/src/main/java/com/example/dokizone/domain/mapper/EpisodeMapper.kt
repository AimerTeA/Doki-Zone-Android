package com.example.dokizone.domain.mapper

import com.example.dokizone.data.remote.model.anime.EpisodeResponse
import com.example.dokizone.domain.model.Episode

fun EpisodeResponse.toEpisode(): Episode {
    return Episode(
        id = malId,
        title = title,
        episode = episode,
        imageUrl = images.jpg.imageUrl.orEmpty()
    )
}