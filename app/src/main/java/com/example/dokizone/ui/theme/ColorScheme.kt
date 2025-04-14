package com.example.dokizone.ui.theme

import androidx.compose.ui.graphics.Color

data class ColorScheme(
    val backgroundColor: Color,
    val textColor: Color,
    val popularNumberTextColor: Color,
    val popularNumberStrokeColor: Color,
    val videoBackground: Color,
    val videoTextColor: Color
)

fun providesLightColorScheme(): ColorScheme {
    return ColorScheme(
        backgroundColor = Color.LightGray50,
        textColor = Color.DarkGray1000,
        popularNumberTextColor = Color.Pink100,
        popularNumberStrokeColor = Color.LightGray50,
        videoBackground = Color.Black,
        videoTextColor = Color.White
    )
}

fun providesDarkColorScheme(): ColorScheme {
    return ColorScheme(
        backgroundColor = Color.DarkGray1000,
        textColor = Color.LightGray50,
        popularNumberTextColor = Color.LightGray50,
        popularNumberStrokeColor = Color.Transparent,
        videoBackground = Color.Black,
        videoTextColor = Color.White
    )
}