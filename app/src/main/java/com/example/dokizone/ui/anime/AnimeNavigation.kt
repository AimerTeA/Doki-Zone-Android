package com.example.dokizone.ui.anime

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.dokizone.ui.anime_details.AnimeDetailsScreen
import com.example.dokizone.ui.utils.launchVideoView

@Composable
fun AnimeNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AnimeScreenRoutes.AnimeScreen
    ) {
        composable<AnimeScreenRoutes.AnimeScreen> { navBackStackEntry ->
            val context = LocalContext.current
            AnimeScreen(
                animatedContentScope = this@composable,
                onVideoClick = {
                    context.launchVideoView(
                        videoId = it.videoUrl,
                        title = it.title
                    )
                },
                navigateToAnimeDetails = { animeId ->
                    navController.navigate(
                        AnimeScreenRoutes.AnimeDetailsScreen(animeId = animeId)
                    )
                }
            )
        }

        composable<AnimeScreenRoutes.AnimeDetailsScreen> { navBackStackEntry ->
            val animeDetailsScreenRoute = navBackStackEntry.toRoute<AnimeScreenRoutes.AnimeDetailsScreen>()
            AnimeDetailsScreen(
                animeId = animeDetailsScreenRoute.animeId,
                animatedContentScope = this@composable,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}