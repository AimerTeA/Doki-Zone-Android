package com.example.dokizone.ui.video

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Rect
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toAndroidRect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.core.content.ContextCompat
import com.example.dokizone.R
import com.example.dokizone.ui.base.BaseActivity
import com.example.dokizone.ui.theme.DokiZoneTheme
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class PiPVideoActivity : BaseActivity() {
    private var videoViewBounds = Rect()
    private var isInPipMode by mutableStateOf(false)
    private var title by mutableStateOf("")
    private var videoId by mutableStateOf("")
    private var youTubePlayer: YouTubePlayer? = null

    private val actionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val isVideoPlaying = intent?.getBooleanExtra(EXTRA_IS_VIDEO_PLAYING, false) ?: false
            if (isVideoPlaying) {
                youTubePlayer?.play()
            } else {
                youTubePlayer?.pause()
            }
            updatePiPActions(isVideoPlaying)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoId = intent.getStringExtra(VIDEO_ID).orEmpty()
        title = intent.getStringExtra(TITLE).orEmpty()
        setContent {
            DokiZoneTheme {
                VideoScreen(
                    videoId = videoId,
                    title = title,
                    videoModifier = Modifier.onGloballyPositioned {
                        videoViewBounds = it.boundsInWindow().toAndroidRect()
                    },
                    isInPipMode = isInPipMode,
                    onPlayerReady = { player ->
                        youTubePlayer = player
                    },
                    onPipListener = {
                        activatePip()
                    },
                    onCloseClicked = {
                        this.finish()
                    }
                )
            }
        }
        val filter = android.content.IntentFilter(ACTION_TOGGLE_PLAY)
        ContextCompat.registerReceiver(
            this,
            actionReceiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        videoId = intent.getStringExtra(VIDEO_ID).orEmpty()
        title = intent.getStringExtra(TITLE).orEmpty()
        youTubePlayer?.loadVideo(videoId = videoId, startSeconds = 0f)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(actionReceiver)
        youTubePlayer = null
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        activatePip()
    }

    override fun onPictureInPictureModeChanged(
        isInPictureInPictureMode: Boolean,
        newConfig: Configuration
    ) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        isInPipMode = isInPictureInPictureMode
    }

    private fun activatePip() {
        val isPipSupported = packageManager
            .hasSystemFeature(PackageManager.FEATURE_PICTURE_IN_PICTURE)
        if (!isPipSupported) {
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            enterPictureInPictureMode(getPipParams(true))
        }
    }

    fun updatePiPActions(isVideoPlaying: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setPictureInPictureParams(getPipParams(isVideoPlaying))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getPipParams(isVideoPlaying: Boolean): PictureInPictureParams {
        return if (isVideoPlaying) {
            updatedPipParams(
                icon = R.drawable.ic_pause,
                title = "pause",
                isVideoPlaying = false
            )
        } else {
            updatedPipParams(
                icon = R.drawable.ic_play,
                title = "play",
                isVideoPlaying = true
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updatedPipParams(
        @DrawableRes icon: Int,
        title: String,
        isVideoPlaying: Boolean,
    ): PictureInPictureParams {
        return PictureInPictureParams.Builder()
            .setSourceRectHint(videoViewBounds)
            .setAspectRatio(Rational(16, 9))
            .setActions(
                listOf(
                    RemoteAction(
                        Icon.createWithResource(
                            applicationContext,
                            icon
                        ),
                        title,
                        title,
                        PendingIntent.getBroadcast(
                            this,
                            0,
                            Intent(ACTION_TOGGLE_PLAY).apply {
                                setPackage(packageName)
                                putExtra(EXTRA_IS_VIDEO_PLAYING, isVideoPlaying)
                            },
                            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                        )
                    )
                )
            )
            .build()
    }

    companion object {
        const val VIDEO_ID = "video_id"
        const val TITLE = "title"

        const val ACTION_TOGGLE_PLAY = "action_toggle_play"
        const val EXTRA_IS_VIDEO_PLAYING = "extra_is_playing"
    }
}