package com.example.dokizone.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeResponse(
    @SerialName("mal_id")
    val malId: Int,
    @SerialName("url")
    val url: String,
    @SerialName("images")
    val images: Images,
    @SerialName("trailer")
    val trailer: Trailer,
    @SerialName("approved")
    val approved: Boolean,
    @SerialName("titles")
    val titles: List<TitleResponse>,
    @SerialName("title")
    val title: String,
    @SerialName("title_english")
    val titleEnglish: String?,
    @SerialName("title_japanese")
    val titleJapanese: String,
    @SerialName("title_synonyms")
    val titleSynonyms: List<String>,
    @SerialName("type")
    val type: String,
    @SerialName("source")
    val source: String,
    @SerialName("episodes")
    val episodes: Int?,
    @SerialName("status")
    val status: String,
    @SerialName("airing")
    val airing: Boolean,
    @SerialName("aired")
    val aired: Aired,
    @SerialName("duration")
    val duration: String,
    @SerialName("rating")
    val rating: String?,
    @SerialName("score")
    val score: Double?,
    @SerialName("scored_by")
    val scoredBy: Int?,
    @SerialName("rank")
    val rank: Int?,
    @SerialName("popularity")
    val popularity: Int,
    @SerialName("members")
    val members: Int,
    @SerialName("favorites")
    val favorites: Int,
    @SerialName("synopsis")
    val synopsis: String?,
    @SerialName("background")
    val background: String,
    @SerialName("season")
    val season: String?,
    @SerialName("year")
    val year: Int?,
    @SerialName("broadcast")
    val broadcast: BroadcastResponse,
    @SerialName("producers")
    val producers: List<ProducerResponse>,
    @SerialName("licensors")
    val licensors: List<MalItemResponse>,
    @SerialName("studios")
    val studios: List<StudioResponse>,
    @SerialName("genres")
    val genres: List<MalItemResponse>,
    @SerialName("explicit_genres")
    val explicitGenres: List<MalItemResponse>,
    @SerialName("themes")
    val themes: List<MalItemResponse>,
    @SerialName("demographics")
    val demographics: List<MalItemResponse>
)