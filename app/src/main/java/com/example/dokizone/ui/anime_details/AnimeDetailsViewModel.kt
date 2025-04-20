package com.example.dokizone.ui.anime_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dokizone.core.addOnFailureListener
import com.example.dokizone.core.addOnSuccessListener
import com.example.dokizone.domain.model.AnimeDetails
import com.example.dokizone.domain.model.Episode
import com.example.dokizone.domain.repository.AnimeRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = AnimeDetailsViewModel.AnimeDetailsViewModelFactory::class)
class AnimeDetailsViewModel @AssistedInject constructor(
    private val animeRepository: AnimeRepository,
    @Assisted private val animeId: Int
): ViewModel() {
    val animeDetails: StateFlow<AnimeDetails?>
        field = MutableStateFlow(null)

    val episodes: StateFlow<List<Episode>>
        field = MutableStateFlow(emptyList())

    val state: StateFlow<AnimeDetailsState>
        field = MutableStateFlow<AnimeDetailsState>(AnimeDetailsState.Loading)

    init {
        viewModelScope.launch {
            animeRepository.getAnimeDetails(animeId)
                .addOnSuccessListener { animeDetails ->
                    this@AnimeDetailsViewModel.animeDetails.value = animeDetails
                    this@AnimeDetailsViewModel.state.value = AnimeDetailsState.Success
                }
                .addOnFailureListener {
                    Log.e("AnimeDetailsViewModel", "Error getting anime details")
                }

            animeRepository.getEpisodes(animeId)
                .addOnSuccessListener { episodes ->
                    this@AnimeDetailsViewModel.episodes.value = episodes
                }
                .addOnFailureListener {
                    Log.e("AnimeDetailsViewModel", "Error getting episodes")
                }
        }
    }

    sealed class AnimeDetailsState {
        object Loading: AnimeDetailsState()
        object Success: AnimeDetailsState()
        object Error: AnimeDetailsState()
    }

    @AssistedFactory
    interface AnimeDetailsViewModelFactory {
        fun create(animeId: Int): AnimeDetailsViewModel
    }
}