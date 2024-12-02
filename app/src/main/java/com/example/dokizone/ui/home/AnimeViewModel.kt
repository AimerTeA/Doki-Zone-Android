package com.example.dokizone.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dokizone.core.addOnFailureListener
import com.example.dokizone.core.addOnSuccessListener
import com.example.dokizone.domain.model.RandomAnime
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

    val randomAnime: StateFlow<RandomAnime?>
        field = MutableStateFlow(null)

    init {
        getRandomAnime()
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
}