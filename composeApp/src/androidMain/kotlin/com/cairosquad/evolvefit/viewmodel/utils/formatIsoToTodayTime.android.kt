package com.cairosquad.evolvefit.viewmodel.utils

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
actual fun formatIsoToTodayTime(isoString: String): String {
    val locale = Locale.getDefault()
    val fixedIso = normalizeIso(isoString)

    val instant = parseIsoInstant(fixedIso) ?: return isoString
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val relativeDay = getRelativeDayLabel(dateTime.date, now.date, locale)
    val timeStr = formatTime(dateTime, locale)

    return if (relativeDay != null) {
        "$relativeDay, $timeStr"
    } else {
        "${formatDate(dateTime, locale)}, $timeStr"
    }
}

/**
 * Normalizes an ISO-8601 datetime string:
 * - Ensures milliseconds are exactly 3 digits.
 * - Ensures a timezone suffix (Z or ±hh:mm) exists.
 */
private fun normalizeIso(iso: String): String {
    return if (iso.contains(".")) {
        val (before, afterAndZone) = iso.split(".", limit = 2)

        val zone = Regex("([+-]\\d{2}:\\d{2}|Z)$")
            .find(afterAndZone)
            ?.value ?: "Z"

        val fraction = afterAndZone.removeSuffix(zone)
        val millis = fraction.padEnd(3, '0').take(3)

        "$before.$millis$zone"
    } else {
        when {
            iso.endsWith("Z") || iso.matches(Regex(".*[+-]\\d{2}:\\d{2}$")) -> iso
            else -> iso + "Z"
        }
    }
}

private fun parseIsoInstant(iso: String): Instant? {
    return try {
        Instant.parse(iso)
    } catch (_: Exception) {
        null
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getRelativeDayLabel(date: LocalDate, today: LocalDate, locale: Locale): String? {
    val diff = date.toEpochDays() - today.toEpochDays()

    return when (diff) {
        0 -> if (locale.language.startsWith("ar")) "اليوم" else "Today"
        -1 -> if (locale.language.startsWith("ar")) "أمس" else "Yesterday"
        in -6..-2 -> {
            val dayOfWeek = date.dayOfWeek
            val formatter = DateTimeFormatter.ofPattern("EEEE", locale)
            dayOfWeek.name
            date.toJavaLocalDate().format(formatter)
        }

        else -> null
    }
}


@RequiresApi(Build.VERSION_CODES.O)
private fun formatTime(dateTime: LocalDateTime, locale: Locale): String {
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a", locale)
    return timeFormatter.format(dateTime.toJavaLocalDateTime())
}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatDate(dateTime: LocalDateTime, locale: Locale): String {
    val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", locale)
    return dateFormatter.format(dateTime.toJavaLocalDateTime())
}

actual fun getCurrentLocale(): String {
    return Locale.getDefault().language
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateDayMonth(isoString: String): String {
    val fixedIso = normalizeIso(isoString)

    val instant = parseIsoInstant(fixedIso) ?: return isoString
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale.ENGLISH)
    return dateFormatter.format(dateTime.toJavaLocalDateTime())
}