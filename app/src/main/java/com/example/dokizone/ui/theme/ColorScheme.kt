package com.example.dokizone.ui.theme

import androidx.compose.ui.graphics.Color

data class ColorScheme(
    val backgroundColor: Color,
    val textColor: Color,
    val popularNumberTextColor: Color,
    val popularNumberStrokeColor: Color,
    val videoBackground: Color,
    val videoTextColor: Color,
    val bottomNavigationColor: Color,
    val selectedNavigationBarIconColor: Color,
    val unselectedNavigationBarIconColor: Color,
    val selectedNavigationBarTextColor: Color,
    val unselectedNavigationBarTextColor: Color,
    val selectedNavigationBarIndicatorColor: Color,
    val tabContainerColor: Color,
    val tabSelectorColor: Color,
    val tabSelectedContentColor: Color,
    val tabUnselectedContentColor: Color
)

fun providesLightColorScheme(): ColorScheme {
    return ColorScheme(
        backgroundColor = Color.LightGray50,
        textColor = Color.DarkGray1000,
        popularNumberTextColor = Color.LightGray50,
        popularNumberStrokeColor = Color.DustyBlue600,
        videoBackground = Color.Black,
        videoTextColor = Color.White,
        bottomNavigationColor = Color.White,
        selectedNavigationBarIndicatorColor = Color.Pink100,
        selectedNavigationBarIconColor = Color.DarkGray1000,
        unselectedNavigationBarIconColor = Color.DarkGray1000,
        selectedNavigationBarTextColor = Color.DarkGray1000,
        unselectedNavigationBarTextColor = Color.DarkGray1000,
        tabContainerColor = Color.LightGray50,
        tabSelectorColor = Color.Pink100,
        tabSelectedContentColor = Color.White,
        tabUnselectedContentColor = Color.White
    )
}

fun providesDarkColorScheme(): ColorScheme {
    return ColorScheme(
        backgroundColor = Color.DarkGray1000,
        textColor = Color.LightGray50,
        popularNumberTextColor = Color.LightGray50,
        popularNumberStrokeColor = Color.Transparent,
        videoBackground = Color.Black,
        videoTextColor = Color.White,
        bottomNavigationColor = Color.DarkGray800,
        selectedNavigationBarIndicatorColor = Color.Pink100,
        selectedNavigationBarIconColor = Color.DarkGray1000,
        unselectedNavigationBarIconColor = Color.LightGray50,
        selectedNavigationBarTextColor = Color.LightGray50,
        unselectedNavigationBarTextColor = Color.LightGray50,
        tabContainerColor = Color.DarkGray800,
        tabSelectorColor = Color.Pink100,
        tabSelectedContentColor = Color.White,
        tabUnselectedContentColor = Color.White
    )
}