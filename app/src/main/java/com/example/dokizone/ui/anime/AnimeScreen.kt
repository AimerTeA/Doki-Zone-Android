package com.example.dokizone.ui.anime

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        val randomAnime by animeViewModel.randomAnime.collectAsStateWithLifecycle()

        randomAnime?.let {
            RandomAnimeCard(
                modifier = Modifier.fillMaxWidth(),
                randomAnime = it
            )
        }
    }
}