package com.example.dokizone.ui.manga

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MangaNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MangaScreenRoutes.MangaScreen
    ) {
        composable<MangaScreenRoutes.MangaScreen> {
            MangaScreen()
        }
    }
}