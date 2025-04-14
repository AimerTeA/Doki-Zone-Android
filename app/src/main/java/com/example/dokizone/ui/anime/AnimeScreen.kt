package com.example.dokizone.ui.anime

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dokizone.R
import com.example.dokizone.domain.model.AnimeCard
import com.example.dokizone.domain.model.PromotionalVideoCard
import com.example.dokizone.ui.components.AsyncImageWithPreview
import com.example.dokizone.ui.components.OutlineText
import com.example.dokizone.ui.components.anime.AnimeCardView
import com.example.dokizone.ui.theme.DokiZoneTheme

@Composable
fun AnimeScreen(
    animeViewModel: AnimeViewModel = hiltViewModel(LocalContext.current as ComponentActivity),
    onVideoClick: (PromotionalVideoCard) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(
            space = DokiZoneTheme.dimens.spaceBetweenRowsHome
        )
    ) {
        val randomAnime by animeViewModel.randomAnime.collectAsStateWithLifecycle()
        val topAnime by animeViewModel.mostPopularAnime.collectAsStateWithLifecycle()
        val currentSeasonAnime by animeViewModel.currentSeasonAnime.collectAsStateWithLifecycle()
        val promotionalVideos by animeViewModel.promotionalVideos.collectAsStateWithLifecycle()

        randomAnime?.let {
            RandomAnimeCard(
                modifier = Modifier.fillMaxWidth(),
                randomAnime = it
            )
        }

        if (!topAnime.isNullOrEmpty()) {
            TopAnimeSection(topAnime = topAnime!!)
        }

        if (!currentSeasonAnime.isNullOrEmpty()) {
            CurrentSeasonAnimeSection(currentSeasonAnime = currentSeasonAnime!!)
        }

        if (!promotionalVideos.isNullOrEmpty()) {
            PromotionalVideosSection(
                promotionalVideos = promotionalVideos!!,
                onVideoClick = onVideoClick
            )
        }

        Spacer(modifier = Modifier.size(0.dp))
    }
}

@Composable
private fun TopAnimeSection(
    topAnime: List<AnimeCard>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = DokiZoneTheme.dimens.spaceBetweenTitleAndRow
        )
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = DokiZoneTheme.dimens.generalPadding
            ),
            text = stringResource(id = R.string.top_anime_section_title),
            style = DokiZoneTheme.typography.rowTitle,
            color = DokiZoneTheme.colorScheme.textColor
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                space = DokiZoneTheme.dimens.spaceBetweenAnimeCards
            )
        ) {
            items(topAnime.size) { index ->
                if (index == 0) {
                    Spacer(modifier = Modifier.width(DokiZoneTheme.dimens.generalPadding))
                }
                TopAnimeCard(animeCard = topAnime[index], index = index + 1)
                if (index == topAnime.size - 1) {
                    Spacer(modifier = Modifier.width(DokiZoneTheme.dimens.generalPadding))
                }
            }
        }
    }
}

@Composable
private fun TopAnimeCard(
    animeCard: AnimeCard,
    index: Int
) {
    Box {
        OutlineText(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(1f),
            text = index.toString(),
            style = DokiZoneTheme.typography.popularNumberAnimeCard,
            color = DokiZoneTheme.colorScheme.popularNumberTextColor,
            strokeColor = DokiZoneTheme.colorScheme.popularNumberStrokeColor
        )
        AnimeCardView(
            animeCard = animeCard
        )
    }
}

@Composable
private fun CurrentSeasonAnimeSection(
    currentSeasonAnime: List<AnimeCard>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = DokiZoneTheme.dimens.spaceBetweenTitleAndRow
        )
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = DokiZoneTheme.dimens.generalPadding
            ),
            text = stringResource(id = R.string.top_current_season_title),
            style = DokiZoneTheme.typography.rowTitle,
            color = DokiZoneTheme.colorScheme.textColor
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                space = DokiZoneTheme.dimens.spaceBetweenAnimeCards
            )
        ) {
            items(currentSeasonAnime.size) { index ->
                if (index == 0) {
                    Spacer(modifier = Modifier.width(DokiZoneTheme.dimens.generalPadding))
                }
                AnimeCardView(animeCard = currentSeasonAnime[index])
                if (index == currentSeasonAnime.size - 1) {
                    Spacer(modifier = Modifier.width(DokiZoneTheme.dimens.generalPadding))
                }
            }
        }
    }
}

@Composable
private fun PromotionalVideosSection(
    promotionalVideos: List<PromotionalVideoCard>,
    onVideoClick: (PromotionalVideoCard) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = DokiZoneTheme.dimens.spaceBetweenTitleAndRow
        )
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = DokiZoneTheme.dimens.generalPadding
            ),
            text = stringResource(id = R.string.promotional_videos_section_title),
            style = DokiZoneTheme.typography.rowTitle,
            color = DokiZoneTheme.colorScheme.textColor
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                space = DokiZoneTheme.dimens.spaceBetweenAnimeCards
            )
        ) {
            items(promotionalVideos.size) { index ->
                if (index == 0) {
                    Spacer(modifier = Modifier.width(DokiZoneTheme.dimens.generalPadding))
                }
                PromotionalVideoCardView(
                    modifier = Modifier.clickable {
                       onVideoClick(promotionalVideos[index])
                    },
                    promotionalVideoCard = promotionalVideos[index]
                )
                if (index == promotionalVideos.size - 1) {
                    Spacer(modifier = Modifier.width(DokiZoneTheme.dimens.generalPadding))
                }
            }
        }
    }
}

@Composable
private fun PromotionalVideoCardView(
    modifier: Modifier = Modifier,
    promotionalVideoCard: PromotionalVideoCard
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = DokiZoneTheme.dimens.spaceBetweenAnimeCards
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            AsyncImageWithPreview(
                modifier = modifier
                    .width(200.dp)
                    .aspectRatio(16/9f)
                    .clip(shape = DokiZoneTheme.shapes.imageShape),
                model = promotionalVideoCard.imageUrl,
                contentDescription = promotionalVideoCard.title,
                contentScale = ContentScale.Crop
            )

            Icon(
                modifier = Modifier.align(Alignment.Center)
                    .size(32.dp),
                painter = painterResource(R.drawable.ic_play),
                contentDescription = null,
                tint = Color.White,
            )
        }
        Text(
            modifier = modifier
                .width(200.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = promotionalVideoCard.title,
            style = DokiZoneTheme.typography.promotionalVideoTitle,
            color = DokiZoneTheme.colorScheme.textColor,
        )
    }
}
