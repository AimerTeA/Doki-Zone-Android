package com.example.dokizone.ui.theme

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCustomColors = staticCompositionLocalOf {
    providesLightColorScheme()
}

val LocalCustomShapes = staticCompositionLocalOf {
    DokiZoneShapes
}

val LocalCustomDimens = staticCompositionLocalOf {
    DokiZoneDimen
}

val LocalTypography = staticCompositionLocalOf {
    DokiZoneTypography
}

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalCurrentTransitionScope = staticCompositionLocalOf<SharedTransitionScope> {
    error("No SharedTransitionScope provided")
}

@Composable
fun DokiZoneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCustomColors provides if (darkTheme) providesDarkColorScheme() else providesLightColorScheme(),
        LocalCustomShapes provides DokiZoneShapes,
        LocalCustomDimens provides DokiZoneDimen,
        LocalTypography provides DokiZoneTypography,
        content = content
    )
}

object DokiZoneTheme {
    val colorScheme: ColorScheme
        @Composable
        get() = LocalCustomColors.current

    val shapes: DokiZoneShapes
        @Composable
        get() = LocalCustomShapes.current

    val dimens: DokiZoneDimen
        @Composable
        get() = LocalCustomDimens.current

    val typography: DokiZoneTypography
        @Composable
        get() = LocalTypography.current
}