package com.example.dokizone.ui.anime

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage

@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val randomAnime by animeViewModel.randomAnime.collectAsStateWithLifecycle()
    AsyncImage(
        model = randomAnime?.image,
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
    )
}