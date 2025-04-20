package com.example.dokizone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dokizone.R
import com.example.dokizone.ui.theme.DarkGray800
import com.example.dokizone.ui.theme.DokiZoneTheme

@Composable
fun ImageVideoView(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String? = null
) {
    Box {
        AsyncImageWithPreview(
            modifier = modifier
                .aspectRatio(16/9f)
                .clip(shape = DokiZoneTheme.shapes.imageShape),
            model = imageUrl,
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier.align(Alignment.Center)
                .size(48.dp)
                .clip(shape = DokiZoneTheme.shapes.circleShape)
                .background(color = Color.DarkGray800.copy(alpha = 0.4f))
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center)
                    .size(32.dp),
                painter = painterResource(R.drawable.ic_play),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}