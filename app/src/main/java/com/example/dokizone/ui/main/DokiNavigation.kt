package com.example.dokizone.ui.main

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.dokizone.ui.anime.AnimeNavigation
import com.example.dokizone.ui.manga.MangaNavigation
import com.example.dokizone.ui.news.NewsNavigation
import com.example.dokizone.ui.theme.DokiZoneTheme
import com.example.dokizone.ui.theme.LocalCurrentTransitionScope
import com.example.dokizone.ui.utils.calculateLayoutType

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DokiNavigation() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val navigationBarItemColors = NavigationBarItemDefaults.colors(
        indicatorColor = DokiZoneTheme.colorScheme.selectedNavigationBarIndicatorColor,
        selectedIconColor = DokiZoneTheme.colorScheme.selectedNavigationBarIconColor,
        selectedTextColor = DokiZoneTheme.colorScheme.selectedNavigationBarTextColor,
        unselectedIconColor = DokiZoneTheme.colorScheme.unselectedNavigationBarIconColor,
        unselectedTextColor = DokiZoneTheme.colorScheme.unselectedNavigationBarTextColor
    )
    val navigationRailItemColors = NavigationRailItemDefaults.colors(
        indicatorColor = DokiZoneTheme.colorScheme.selectedNavigationBarIndicatorColor,
        selectedIconColor = DokiZoneTheme.colorScheme.selectedNavigationBarIconColor,
        selectedTextColor = DokiZoneTheme.colorScheme.selectedNavigationBarTextColor,
        unselectedIconColor = DokiZoneTheme.colorScheme.unselectedNavigationBarIconColor,
        unselectedTextColor = DokiZoneTheme.colorScheme.unselectedNavigationBarTextColor
    )
    val navigationDrawerItemColors = NavigationDrawerItemDefaults.colors()
    Scaffold { innerPadding ->
        NavigationSuiteScaffold(
            containerColor = DokiZoneTheme.colorScheme.backgroundColor,
            navigationSuiteColors = NavigationSuiteDefaults.colors(
                navigationBarContainerColor = DokiZoneTheme.colorScheme.bottomNavigationColor,
                navigationRailContainerColor = DokiZoneTheme.colorScheme.bottomNavigationColor,
            ),
            modifier = Modifier.padding(
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                end = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                bottom = innerPadding.calculateBottomPadding()
            ),
            navigationSuiteItems = {
                TabScreen.tabs.forEachIndexed { index, screen ->
                    item(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                        },
                        icon = {
                            Icon(
                                painter = painterResource(screen.icon),
                                contentDescription = screen.title,
                                modifier = Modifier.size(32.dp)
                            )
                        },
                        label = {
                            Text(
                                text = screen.title,
                                style = DokiZoneTheme.typography.navigationText
                            )
                        },
                        colors = NavigationSuiteItemColors(
                            navigationBarItemColors = navigationBarItemColors,
                            navigationRailItemColors = navigationRailItemColors,
                            navigationDrawerItemColors = navigationDrawerItemColors
                        )
                    )
                }
            },
            layoutType = calculateLayoutType(),
            content = {
                SharedTransitionLayout {
                    CompositionLocalProvider(
                        LocalCurrentTransitionScope provides this
                    ) {
                        InnerNavigation(selectedItem)
                    }
                }
            }
        )
    }
}

@Composable
private fun InnerNavigation(selectedItem: Int) {
    val startDestination = TabScreen.tabs[selectedItem]
    val animeNavController = rememberNavController()
    val mangaNavController = rememberNavController()
    val newsNavController = rememberNavController()
    when (startDestination) {
        is TabScreen.AnimeTab ->  AnimeNavigation(animeNavController)
        is TabScreen.MangaTab -> MangaNavigation(mangaNavController)
        is TabScreen.NewsTab -> NewsNavigation(newsNavController)
    }
}