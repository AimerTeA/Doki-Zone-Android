package com.example.dokizone.ui.theme

import androidx.compose.ui.graphics.Color

data class ColorScheme(
    val backgroundColor: Color
)

fun providesLightColorScheme(): ColorScheme {
    return ColorScheme(
        backgroundColor = Color.LightGray50
    )
}

fun providesDarkColorScheme(): ColorScheme {
    return ColorScheme(
        backgroundColor = Color.DarkGray900
    )
}