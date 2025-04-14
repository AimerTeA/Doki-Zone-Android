package com.example.dokizone.ui.anime

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dokizone.core.addOnFailureListener
import com.example.dokizone.core.addOnSuccessListener
import com.example.dokizone.domain.model.AnimeCard
import com.example.dokizone.domain.model.PromotionalVideoCard
import com.example.dokizone.domain.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val animeRepository: AnimeRepository
) : ViewModel() {
    private val tag = AnimeViewModel::class.java.simpleName

    val randomAnime: StateFlow<AnimeCard?>
        field = MutableStateFlow(null)

    val mostPopularAnime: StateFlow<List<AnimeCard>?>
        field = MutableStateFlow(null)

    val currentSeasonAnime: StateFlow<List<AnimeCard>?>
        field = MutableStateFlow(null)

    val promotionalVideos: StateFlow<List<PromotionalVideoCard>?>
        field = MutableStateFlow(null)

    init {
        getRandomAnime()
        getTopAnime()
        getCurrentSeasonAnime()
        getPromotionalVideos()
    }

    private fun getRandomAnime() {
        viewModelScope.launch {
            animeRepository.getRandomAnime().addOnSuccessListener { randomAnime ->
                this@AnimeViewModel.randomAnime.value = randomAnime
            }.addOnFailureListener { exception ->
                // Handle the error here
                Log.d(tag, "getRandomAnime: $exception")
            }
        }
    }

    private fun getTopAnime() {
        viewModelScope.launch {
            animeRepository.getMostPopularAnime().addOnSuccessListener { topAnime ->
                this@AnimeViewModel.mostPopularAnime.value = topAnime
            }
        }
    }

    private fun getCurrentSeasonAnime() {
        viewModelScope.launch {
            animeRepository.getCurrentSeasonAnime().addOnSuccessListener { currentSeasonAnime ->
                this@AnimeViewModel.currentSeasonAnime.value = currentSeasonAnime
            }
        }
    }

    private fun getPromotionalVideos() {
        viewModelScope.launch {
            animeRepository.getPromotionalVideos().addOnSuccessListener { promotionalVideos ->
                this@AnimeViewModel.promotionalVideos.value = promotionalVideos
            }
        }
    }
}