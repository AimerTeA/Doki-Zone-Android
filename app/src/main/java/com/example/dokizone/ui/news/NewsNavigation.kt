package com.example.dokizone.ui.news

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dokizone.ui.main.TabScreen

fun NavGraphBuilder.NewsNavigation(
    navController: NavController
) {
    navigation<TabScreen.NewsTab>(
        startDestination = NewsScreenRoutes.NewsScreen
    ) {
        composable<NewsScreenRoutes.NewsScreen> {
            NewsScreen()
        }
    }
}