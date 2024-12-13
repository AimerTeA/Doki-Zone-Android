package com.example.dokizone.ui

import android.content.res.Configuration
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

@Composable
fun calculateLayoutType(): NavigationSuiteType {
    return if (isWideScreenMode()) {
        NavigationSuiteType.NavigationRail
    } else {
        NavigationSuiteType.NavigationBar
    }
}

@Composable
fun isTablet(): Boolean {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val windowWidthSizeClass = windowSizeClass.windowWidthSizeClass
    val windowHeightSizeClass = windowSizeClass.windowHeightSizeClass
    return windowHeightSizeClass != WindowHeightSizeClass.COMPACT && windowWidthSizeClass != WindowWidthSizeClass.COMPACT
}

@Composable
fun isLandscapeOrientation(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

@Composable
fun isWideScreenMode(): Boolean {
    return isTablet() || isLandscapeOrientation()
}

@Composable
fun isPhoneInLandscape(): Boolean {
    return !isTablet() && isLandscapeOrientation()
}