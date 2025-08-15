package com.cairosquad.evolvefit.viewmodel.utils

import kotlinx.datetime.*
import platform.Foundation.*

actual fun formatIsoToTodayTime(isoString: String): String {
    val locale = NSLocale.currentLocale
    val dateFormatter = NSDateFormatter().apply {
        dateFormat = "yyyy-MM-dd'T'HH:mm:ss"
        this.locale = NSLocale(localeIdentifier = "en_US_POSIX")
        timeZone = NSTimeZone.timeZoneForSecondsFromGMT(0)
    }

    val date = dateFormatter.dateFromString(isoString) ?: return isoString
    val calendar = NSCalendar.currentCalendar
    val now = NSDate()

    val dayDiff = calendar.components(
        NSCalendarUnitDay,
        fromDate = date,
        toDate = now,
        options = 0u
    ).day

    val relativeDay = when (dayDiff) {
        0L -> if (locale.languageCode == "ar") "اليوم" else "Today"
        -1L -> if (locale.languageCode == "ar") "أمس" else "Yesterday"
        else -> {
            val outFormatter = NSDateFormatter().apply {
                dateFormat = "MMM dd, yyyy"
                this.locale = locale
            }
            outFormatter.stringFromDate(date)
        }
    }

    val timeFormatter = NSDateFormatter().apply {
        dateFormat = "hh:mm a"
        this.locale = locale
    }
    val timeStr = timeFormatter.stringFromDate(date)

    return "$relativeDay، $timeStr"
}

actual fun getCurrentLocale(): String {
    return NSLocale.currentLocale.languageCode ?: "en"
}