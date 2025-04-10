package com.example.dokizone.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dokizone.R

// Set of Material typography styles to start with
val funnelSansFamily = FontFamily(
    Font(resId = R.font.funnel_sans_light, weight = FontWeight.Light),
    Font(resId = R.font.funnel_sans_regular, weight = FontWeight.Normal),
    Font(resId = R.font.funnel_sans_medium, weight = FontWeight.Medium),
    Font(resId = R.font.funnel_sans_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.funnel_sans_bold, weight = FontWeight.Bold),
    Font(resId = R.font.funnel_sans_extra_bold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.funnel_sans_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(resId = R.font.funnel_sans_italic, style = FontStyle.Italic),
    Font(resId = R.font.funnel_sans_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(resId = R.font.funnel_sans_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(resId = R.font.funnel_sans_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(resId = R.font.funnel_sans_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
)

@Immutable
object DokiZoneTypography {
    val title = TextStyle(
        fontFamily = funnelSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    )
    val synopsis = TextStyle(
        fontFamily = funnelSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
    val rowTitle = TextStyle(
        fontFamily = funnelSansFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    )
    val popularNumberAnimeCard = TextStyle(
        fontFamily = funnelSansFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 72.sp,
        lineHeight = 72.sp
    )
    val promotionalVideoTitle = TextStyle(
        fontFamily = funnelSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
}