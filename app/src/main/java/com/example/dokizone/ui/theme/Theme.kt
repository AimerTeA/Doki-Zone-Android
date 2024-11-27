package com.example.dokizone.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalCustomColors = staticCompositionLocalOf {
    providesLightColorScheme()
}

@Composable
fun DokiZoneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalCustomColors provides if (darkTheme) providesDarkColorScheme() else providesLightColorScheme(),
        content = content
    )
}

object DokiZoneTheme {
    val colorScheme: ColorScheme
        @Composable
        get() = LocalCustomColors.current
}