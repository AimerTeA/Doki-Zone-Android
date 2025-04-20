package com.example.dokizone.ui.news

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NewsNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NewsScreenRoutes.NewsScreen
    ) {
        composable<NewsScreenRoutes.NewsScreen> {
            NewsScreen()
        }
    }
}