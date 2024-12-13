package com.example.dokizone.data.local.time

import android.content.SharedPreferences
import java.util.Calendar
import javax.inject.Inject

class TimeLocalDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val TIME_RANDOM_ANIME_SAVED = "time_random_anime_saved"
    }

    fun updateTimeRandomAnimeSaved() {
        sharedPreferences.edit().putLong(TIME_RANDOM_ANIME_SAVED, System.currentTimeMillis()).apply()
    }

    fun shouldUpdateRandomAnime(): Boolean {
        val lastSavedTime = sharedPreferences.getLong(TIME_RANDOM_ANIME_SAVED, 0)
        val lastSavedDate = Calendar.getInstance().apply {
            timeInMillis = lastSavedTime
        }
        val lastSavedDay = lastSavedDate.get(Calendar.DAY_OF_YEAR)

        val currentDate = Calendar.getInstance()
        val currentDay = currentDate.get(Calendar.DAY_OF_YEAR)

        return currentDay != lastSavedDay
    }
}