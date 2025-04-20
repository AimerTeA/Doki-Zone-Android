package com.example.dokizone.ui.anime_details

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dokizone.R
import com.example.dokizone.ui.components.AsyncImageWithPreview
import com.example.dokizone.ui.components.CollapsingLayout
import com.example.dokizone.ui.theme.DokiZoneTheme
import com.example.dokizone.ui.theme.LocalCurrentTransitionScope

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class,
    ExperimentalFoundationApi::class, ExperimentalMaterial3AdaptiveApi::class
)
@Composable
fun AnimeDetailsScreen(
    animatedContentScope: AnimatedContentScope? = null,
    animeId: Int,
    navigateBack: () -> Unit = {}
) {
    val animeDetailsViewModel = hiltViewModel<AnimeDetailsViewModel, AnimeDetailsViewModel.AnimeDetailsViewModelFactory> {
        it.create(animeId)
    }
    val animeDetails by animeDetailsViewModel.animeDetails.collectAsStateWithLifecycle()
    val episodes by animeDetailsViewModel.episodes.collectAsStateWithLifecycle()
    val state by animeDetailsViewModel.state.collectAsStateWithLifecycle()

    if (state is AnimeDetailsViewModel.AnimeDetailsState.Loading) {
        Text(text = "Loading")
        return
    }

    if (state is AnimeDetailsViewModel.AnimeDetailsState.Error) {
        return
    }

    var tabIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier
            .background(DokiZoneTheme.colorScheme.backgroundColor)
            .fillMaxSize(),
        topBar = {

        }
    ) { paddingValues ->
        CollapsingLayout(
            modifier = Modifier
                .background(DokiZoneTheme.colorScheme.backgroundColor)
                .fillMaxWidth()
                .padding(
                    start = paddingValues.calculateLeftPadding(LayoutDirection.Ltr),
                    end = paddingValues.calculateRightPadding(LayoutDirection.Ltr),
                    bottom = paddingValues.calculateBottomPadding()
                ),
            toolbarTitle = animeDetails!!.title,
            onNavigationIconClicked =  navigateBack,
            collapsingTop = {
                with(LocalCurrentTransitionScope.current) {
                    Column {
                        AsyncImageWithPreview(
                            modifier = Modifier.fillMaxWidth()
                                .sharedElement(
                                    state = rememberSharedContentState(animeId),
                                    animatedVisibilityScope = animatedContentScope!!
                                ),
                            contentScale = ContentScale.FillWidth,
                            model = animeDetails?.imageUrl,
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .padding(
                                    horizontal = DokiZoneTheme.dimens.cardHorizonalSpace
                                ),
                            text = animeDetails!!.synopsis,
                            style = DokiZoneTheme.typography.bodyText,
                            color = DokiZoneTheme.colorScheme.textColor,
                            maxLines = 4
                        )
                    }
                }
            },
            bodyContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    TabRow(
                        selectedTabIndex = tabIndex,
                        containerColor = DokiZoneTheme.colorScheme.tabContainerColor,
                        indicator = {
                            TabRowDefaults.SecondaryIndicator(
                                modifier = Modifier.tabIndicatorOffset(it[tabIndex]),
                                color = DokiZoneTheme.colorScheme.tabSelectorColor
                            )
                        }
                    ) {
                        listOf(
                            stringResource(R.string.episodes), "2").forEachIndexed { index, item ->
                            Tab(
                                selected = tabIndex == index,
                                text = {
                                    Text(
                                        text = item,
                                        style = DokiZoneTheme.typography.tabBar,
                                        color = DokiZoneTheme.colorScheme.textColor
                                    )
                                },
                                onClick = {
                                    tabIndex = index
                                },
                                selectedContentColor = DokiZoneTheme.colorScheme.tabSelectedContentColor,
                                unselectedContentColor = DokiZoneTheme.colorScheme.tabUnselectedContentColor
                            )
                        }
                    }
                    when (tabIndex) {
                        0 -> {
                            EpisodesTab(
                                episodes = episodes
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
@Preview
fun AnimeDetailsScreenPreview() {
    DokiZoneTheme {
        AnimeDetailsScreen(
            animeId = 1
        )
    }
}