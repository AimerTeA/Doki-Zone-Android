package com.example.dokizone.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.dokizone.ui.video.VideoController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YouTubeVideoPlayer(
    modifier: Modifier = Modifier,
    videoId: String,
    onPlayerReady: ((YouTubePlayer) -> Unit)? = null,
    onPipListener: () -> Unit
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
                            val defaultViewController = VideoController(this@apply, youTubePlayer)
                            defaultViewController.addOnPipClickListener {
                                onPipListener()
                            }
                            this@apply.setCustomPlayerUi(defaultViewController.getRootView())
                            onPlayerReady?.invoke(youTubePlayer)
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
                        .controls(0)
                        .ivLoadPolicy(3) // Hide video annotations
                        .fullscreen(1)
                        .build()
                )
            }
        },
        update = {
            it.context
        }
    )
}