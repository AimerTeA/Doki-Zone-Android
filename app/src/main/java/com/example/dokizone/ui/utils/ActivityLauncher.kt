package com.example.dokizone.ui.utils

import android.content.Context
import android.content.Intent
import com.example.dokizone.ui.video.PiPVideoActivity

fun Context.launchVideoView(
    videoId: String,
    title: String
) {
    val intent = Intent(this, PiPVideoActivity::class.java).apply {
        putExtra(PiPVideoActivity.VIDEO_ID, videoId)
        putExtra(PiPVideoActivity.TITLE, title)
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
    }
    startActivity(intent)
}