package com.example.dokizone.ui.anime

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dokizone.ui.main.TabScreen
import com.example.dokizone.ui.video.VideoScreen

fun NavGraphBuilder.AnimeNavigation(
    navController: NavController
) {
    navigation<TabScreen.AnimeTab>(
        startDestination = AnimeScreenRoutes.AnimeScreen
    ) {
        composable<AnimeScreenRoutes.AnimeScreen> { navBackStackEntry ->
            AnimeScreen(
                onVideoClick = {
                    navController.navigate(
                        AnimeScreenRoutes.VideoScreen(
                            videoId = it.videoUrl,
                            title = it.title
                        )
                    )
                }
            )
        }
        composable<AnimeScreenRoutes.VideoScreen> {
            val videoId = it.arguments?.getString("videoId")
            val title = it.arguments?.getString("title")
            if (videoId != null && title != null) {
                VideoScreen(
                    videoId = videoId,
                    title = title
                )
            }
        }
    }
}