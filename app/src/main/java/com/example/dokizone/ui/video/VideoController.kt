package com.example.dokizone.ui.video

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.widget.SeekBar
import com.example.dokizone.R
import com.example.dokizone.databinding.YoutubeControllerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import androidx.core.view.isVisible
import androidx.core.view.postDelayed
import com.pierfrancescosoffritti.androidyoutubeplayer.core.customui.utils.TimeUtilities

class VideoController(
    private val youTubePlayerView: YouTubePlayerView,
    private val youTubePlayer: YouTubePlayer,
) {
    private val youtubeControllerBinding: YoutubeControllerBinding = YoutubeControllerBinding.inflate(
        LayoutInflater.from(youTubePlayerView.context)
    )

    private var isPlaying = false
    private var isSeekBarBeingScrolled = false

    private val youTubePlayerStateListener = object : AbstractYouTubePlayerListener() {
        override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
            updateState(state)
        }

        override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {

        }

        override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
            super.onVideoDuration(youTubePlayer, duration)
            youtubeControllerBinding.tvFullTime.text = TimeUtilities.formatTime(duration)
            youtubeControllerBinding.sbProgress.max = duration.toInt()
        }

        override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
            super.onCurrentSecond(youTubePlayer, second)
            if (!isSeekBarBeingScrolled) {
                youtubeControllerBinding.sbProgress.progress = second.toInt()
            }

        }

        override fun onVideoLoadedFraction(youTubePlayer: YouTubePlayer, loadedFraction: Float) {
            super.onVideoLoadedFraction(youTubePlayer, loadedFraction)
            youtubeControllerBinding.sbProgress.secondaryProgress = (loadedFraction *  youtubeControllerBinding.sbProgress.max).toInt()
        }
    }

    init {
        youTubePlayer.addListener(youTubePlayerStateListener)
        youtubeControllerBinding.root.setOnClickListener {
            if (youtubeControllerBinding.controlsContainer.isVisible) {
                youtubeControllerBinding.controlsContainer.visibility = View.GONE
            } else {
                youtubeControllerBinding.controlsContainer.alpha = 1f
                youtubeControllerBinding.controlsContainer.visibility = View.VISIBLE
                youtubeControllerBinding.controlsContainer.postDelayed(
                    3000,
                    {
                        youtubeControllerBinding.controlsContainer
                            .animate()
                            .alpha(0f)
                            .setDuration(1000)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    super.onAnimationEnd(animation)
                                    youtubeControllerBinding.controlsContainer.visibility = View.GONE
                                }
                            })
                    }
                )
            }
        }
        youtubeControllerBinding.ivPlayPause.setOnClickListener {
            if (isPlaying) {
                youTubePlayer.pause()
            } else {
                youTubePlayer.play()
            }
        }
        youtubeControllerBinding.sbProgress.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                p0: SeekBar?,
                p1: Int,
                p2: Boolean,
            ) {
                youtubeControllerBinding.tvTime.text = TimeUtilities.formatTime(p0!!.progress.toFloat())
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                isSeekBarBeingScrolled = true
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                youTubePlayer.seekTo(p0!!.progress.toFloat())
            }
        })
    }

    private fun updateState(state: PlayerConstants.PlayerState) {
        when (state) {
            PlayerConstants.PlayerState.ENDED -> isPlaying = false
            PlayerConstants.PlayerState.PAUSED -> isPlaying = false
            PlayerConstants.PlayerState.PLAYING -> isPlaying = true
            else -> {}
        }

        if (state == PlayerConstants.PlayerState.UNSTARTED) {
            youtubeControllerBinding.groupControllers.visibility = View.GONE
        } else {
            youtubeControllerBinding.groupControllers.visibility = View.VISIBLE
        }

        if (isPlaying && youtubeControllerBinding.sbProgress.progress == 0) {
            youtubeControllerBinding.controlsContainer.visibility = View.GONE
        }

        updatePlayPauseButtonIcon(isPlaying)
    }

    private fun updatePlayPauseButtonIcon(playing: Boolean) {
        val drawable = if (playing) R.drawable.ic_pause else R.drawable.ic_play
        youtubeControllerBinding.ivPlayPause.setImageResource(drawable)
    }

    fun getRootView() = youtubeControllerBinding.root

    fun addOnPipClickListener(onPipListener: () -> Unit) {
        youtubeControllerBinding.btnPip.setOnClickListener {

            onPipListener()
        }
    }
}