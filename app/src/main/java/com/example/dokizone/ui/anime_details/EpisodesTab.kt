package com.example.dokizone.ui.anime_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dokizone.domain.model.Episode
import com.example.dokizone.ui.components.AsyncImageWithPreview
import com.example.dokizone.ui.components.ImageVideoView
import com.example.dokizone.ui.theme.DokiZoneTheme

@Composable
fun EpisodesTab(
    episodes: List<Episode>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(episodes.size) {
            EpisodeItem(episode = episodes[it])
        }
    }
}

@Composable
private fun EpisodeItem(episode: Episode) {
    Row(
        modifier = Modifier.padding(
            vertical = DokiZoneTheme.dimens.episodePadding,
            horizontal = DokiZoneTheme.dimens.generalPadding
        ),
        horizontalArrangement = Arrangement.spacedBy(DokiZoneTheme.dimens.cardHorizonalSpace),
    ) {
        ImageVideoView(
            modifier = Modifier.width(200.dp),
            imageUrl = episode.imageUrl
        )

        Column {
            Text(
                text = episode.title,
                style = DokiZoneTheme.typography.bodyText,
                color = DokiZoneTheme.colorScheme.textColor
            )
            Text(
                text = episode.episode,
                style = DokiZoneTheme.typography.bodyText,
                color = DokiZoneTheme.colorScheme.textColor
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun EpisodesTabPreview() {
    DokiZoneTheme {
        EpisodesTab(
            episodes = listOf(
                Episode(
                    id = 1,
                    title = "Episode Tile",
                    episode = "Episode 1",
                    imageUrl = ""
                )
            ),
        )
    }
}