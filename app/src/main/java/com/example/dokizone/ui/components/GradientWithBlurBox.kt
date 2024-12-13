package com.example.dokizone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dokizone.ui.theme.DokiZoneTheme
import com.example.dokizone.ui.theme.White10
import com.example.dokizone.ui.theme.White40

@Composable
fun GradientWithBlurBox(
    modifier: Modifier = Modifier,
    background: Brush,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(background)
    ) {
        Card(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White40,
                            Color.White10,
                            DokiZoneTheme.colorScheme.backgroundColor
                        )
                    )
                )
                .blur(
                    5.dp,
                    edgeTreatment = BlurredEdgeTreatment.Rectangle
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {}
        content()
    }
}