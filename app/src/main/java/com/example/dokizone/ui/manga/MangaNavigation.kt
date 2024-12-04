package com.example.dokizone.ui.manga

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dokizone.ui.main.TabScreen

fun NavGraphBuilder.MangaNavigation(
    navController: NavController
) {
    navigation<TabScreen.MangaTab>(
        startDestination = MangaScreenRoutes.MangaScreen
    ) {
        composable<MangaScreenRoutes.MangaScreen> {
            MangaScreen()
        }
    }
}