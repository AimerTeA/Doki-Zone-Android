package com.example.dokizone.ui.anime

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dokizone.ui.main.TabScreen

fun NavGraphBuilder.AnimeNavigation(
    navController: NavController
) {
    navigation<TabScreen.AnimeTab>(
        startDestination = AnimeScreenRoutes.AnimeScreen
    ) {
        composable<AnimeScreenRoutes.AnimeScreen> { navBackStackEntry ->
            AnimeScreen()
        }
    }
}