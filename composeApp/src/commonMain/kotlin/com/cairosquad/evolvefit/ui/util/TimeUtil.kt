package com.cairosquad.evolvefit.ui.util

object TimeUtil {
    fun formatDurationLabel(seconds: Float): String {
        val totalSeconds = seconds.toLong()
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val secs = totalSeconds % 60

        return when {
            hours > 0 && minutes == 0L -> "$hours hr"
            hours > 0 && minutes > 0L -> "$hours hr $minutes min"
            minutes > 0 -> "$minutes min"
            else -> "$secs sec"
        }
    }

}