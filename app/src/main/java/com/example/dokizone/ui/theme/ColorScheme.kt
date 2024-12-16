package com.example.dokizone.ui.theme

import androidx.compose.ui.graphics.Color

data class ColorScheme(
    val backgroundColor: Color,
    val textColor: Color,
    val popularNumberTextColor: Color
)

fun providesLightColorScheme(): ColorScheme {
    return ColorScheme(
        backgroundColor = Color.LightGray50,
        textColor = Color.DarkGray900,
        popularNumberTextColor = Color.Orange500
    )
}

fun providesDarkColorScheme(): ColorScheme {
    return ColorScheme(
        backgroundColor = Color.DarkGray900,
        textColor = Color.LightGray50,
        popularNumberTextColor = Color.Orange300
    )
}