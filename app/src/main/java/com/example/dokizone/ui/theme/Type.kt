package com.example.dokizone.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.dokizone.R

// Set of Material typography styles to start with
val fontFamily = FontFamily(
    Font(resId = R.font.nunito_extra_light, weight = FontWeight.ExtraLight),
    Font(resId = R.font.nunito_light, weight = FontWeight.Light),
    Font(resId = R.font.nunito_regular, weight = FontWeight.Normal),
    Font(resId = R.font.nunito_medium, weight = FontWeight.Medium),
    Font(resId = R.font.nunito_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.nunito_bold, weight = FontWeight.Bold),
    Font(resId = R.font.nunito_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.nunito_black, weight = FontWeight.Black),
    Font(resId = R.font.nunito_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
)

@Immutable
object DokiZoneTypography {
    val title = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
    val synopsis = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
    val rowTitle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    )
    val popularNumberAnimeCard = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 48.sp,
        lineHeight = 48.sp
    )
    val promotionalVideoTitle = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
}