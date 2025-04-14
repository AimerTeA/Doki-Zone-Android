package com.example.dokizone.ui.video

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dokizone.R
import com.example.dokizone.ui.components.YouTubeVideoPlayer
import com.example.dokizone.ui.theme.DokiZoneTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

@Composable
fun VideoScreen(
    videoId: String,
    title: String,
    videoModifier: Modifier = Modifier,
    isInPipMode: Boolean,
    onPlayerReady: ((YouTubePlayer) -> Unit)? = null,
    onPipListener: () -> Unit = {},
    onCloseClicked: () -> Unit = {}
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = DokiZoneTheme.colorScheme.videoBackground)
                .padding(top = if (!isInPipMode) 32.dp else 0.dp),
        ) {
            if (!isInPipMode) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    onCloseClicked()
                                }
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(top = paddingValues.calculateTopPadding())
                            .padding(horizontal = 32.dp),
                        text = title,
                        style = DokiZoneTheme.typography.title,
                        color = DokiZoneTheme.colorScheme.videoTextColor
                    )
                }
            }
            YouTubeVideoPlayer(
                modifier = videoModifier.fillMaxSize().align(Alignment.Center),
                videoId = videoId,
                onPlayerReady = onPlayerReady,
                onPipListener = onPipListener
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun VideoScreenPreview() {
    DokiZoneTheme {
        VideoScreen(
            videoId = "12345",
            title = "Example Video",
            isInPipMode = false
        )
    }
}