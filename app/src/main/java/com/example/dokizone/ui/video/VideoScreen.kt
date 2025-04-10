package com.example.dokizone.ui.video

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dokizone.ui.components.YouTubeVideoPlayer
import com.example.dokizone.ui.theme.DokiZoneTheme

@Composable
fun VideoScreen(
    videoId: String,
    title: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 20.dp,
            Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = DokiZoneTheme.typography.title,
            color = DokiZoneTheme.colorScheme.textColor
        )
        YouTubeVideoPlayer(
            modifier = Modifier.fillMaxWidth(),
            videoId = videoId
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun VideoScreenPreview() {
    DokiZoneTheme {
        VideoScreen(
            videoId = "12345",
            title = "Example Video"
        )
    }
}