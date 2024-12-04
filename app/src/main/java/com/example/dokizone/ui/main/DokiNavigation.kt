package com.example.dokizone.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.dokizone.ui.anime.AnimeNavigation
import com.example.dokizone.ui.manga.MangaNavigation
import com.example.dokizone.ui.news.NewsNavigation
import com.example.dokizone.ui.theme.DokiZoneTheme

@Composable
fun DokiNavigation() {
    var selectedItem by remember { mutableIntStateOf(0) }
    Scaffold { innerPadding ->
        NavigationSuiteScaffold(
            containerColor = DokiZoneTheme.colorScheme.backgroundColor,
            modifier = Modifier.padding(innerPadding),
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
                                modifier = Modifier.size(60.dp)
                            )
                        },
                        label = {
                            Text(
                                text = screen.title,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    )
                }
            },
            layoutType = calculateLayoutType(),
            content = {
                InnerNavigation(selectedItem)
            }
        )
    }
}

@Composable
private fun calculateLayoutType(): NavigationSuiteType {
    val windowWidthSizeClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    return if (windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
        NavigationSuiteType.NavigationDrawer
    } else {
        NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(currentWindowAdaptiveInfo())
    }
}

@Composable
private fun InnerNavigation(selectedItem: Int) {
    val navController = rememberNavController()
    val startDestination = TabScreen.tabs[selectedItem]
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        AnimeNavigation(navController)

        MangaNavigation(navController)

        NewsNavigation(navController)
    }
}