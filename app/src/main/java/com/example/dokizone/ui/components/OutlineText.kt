package com.example.dokizone.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.zIndex

@Composable
fun OutlineText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = LocalTextStyle.current,
    color: Color = Color.Unspecified,
    strokeColor: Color = Color.Unspecified
) {
    Box(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(1f),
            text = text,
            style = style,
            color = color
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(1f),
            text = text,
            style = style
                .copy(
                    drawStyle = Stroke(width = 8f)
                ),
            color = strokeColor,
        )
    }
}