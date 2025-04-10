package com.example.dokizone.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubeVideoPlayer(
    modifier: Modifier = Modifier,
    videoId: String
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val playerState = rememberSaveable { mutableMapOf<String, Any>() }
    AndroidView(
        modifier = modifier,
        factory = { context ->
            YouTubePlayerView(context).apply {
                lifecycleOwner.lifecycle.addObserver(this)
                enableAutomaticInitialization = false
                initialize(
                    youTubePlayerListener = object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)
                            if (playerState["pause"] == true) {
                                youTubePlayer.cueVideo(
                                    videoId = videoId,
                                    startSeconds = playerState["time"] as? Float ?: 0f
                                )
                            } else {
                                youTubePlayer.loadVideo(
                                    videoId = videoId,
                                    startSeconds = playerState["time"] as? Float ?: 0f
                                )
                            }
                        }

                        override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                            super.onCurrentSecond(youTubePlayer, second)
                            playerState["time"] = second
                        }

                        override fun onStateChange(
                            youTubePlayer: YouTubePlayer,
                            state: PlayerConstants.PlayerState
                        ) {
                            super.onStateChange(youTubePlayer, state)
                            when (state) {
                                PlayerConstants.PlayerState.PLAYING -> playerState["pause"] = false
                                PlayerConstants.PlayerState.PAUSED -> playerState["pause"] = true
                                else -> {}
                            }
                        }
                    },
                    playerOptions = IFramePlayerOptions
                        .Builder()
                        .rel(0)
                        .ivLoadPolicy(3) // Hide video annotations
                        .build()
                )
            }
        }
    )
}