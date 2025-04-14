package com.example.dokizone.ui.anime

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dokizone.ui.main.TabScreen
import com.example.dokizone.ui.utils.launchVideoView

fun NavGraphBuilder.AnimeNavigation(
    navController: NavController
) {
    navigation<TabScreen.AnimeTab>(
        startDestination = AnimeScreenRoutes.AnimeScreen
    ) {
        composable<AnimeScreenRoutes.AnimeScreen> { navBackStackEntry ->
            val context = LocalContext.current
            AnimeScreen(
                onVideoClick = {
                    context.launchVideoView(
                        videoId = it.videoUrl,
                        title = it.title
                    )
                }
            )
        }
    }
}