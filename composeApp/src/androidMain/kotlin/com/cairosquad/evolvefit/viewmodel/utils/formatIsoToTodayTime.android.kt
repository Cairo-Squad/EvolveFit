package com.cairosquad.evolvefit.viewmodel.utils

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.*
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
actual fun formatIsoToTodayTime(isoString: String): String {
    val locale = Locale.getDefault()
    val dateTime = try {
        LocalDateTime.parse(isoString)
    } catch (e: Exception) {
        return isoString
    }

    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val date = dateTime.date
    val today = now.date
    val diff = date.toEpochDays() - today.toEpochDays()

    val relativeDay = when(diff) {
        0 -> if (locale.language.startsWith("ar")) "اليوم" else "Today"
        -1 -> if (locale.language.startsWith("ar")) "أمس" else "Yesterday"
        else ->   { val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", locale)
        formatter.format(dateTime.toJavaLocalDateTime())}
    }

    val timeFormatter = DateTimeFormatter.ofPattern(
        if (locale.language.startsWith("ar")) "hh:mm a" else "hh:mm a",
        locale
    )

    val timeStr = timeFormatter.format(dateTime.toJavaLocalDateTime().toLocalTime())

    return "$relativeDay, $timeStr"
}

actual fun getCurrentLocale(): String {
    return Locale.getDefault().language
}