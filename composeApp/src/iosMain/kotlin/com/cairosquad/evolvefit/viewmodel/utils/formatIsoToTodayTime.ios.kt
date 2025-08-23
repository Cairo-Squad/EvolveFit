package com.cairosquad.evolvefit.viewmodel.utils

import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitDay
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.Foundation.localTimeZone

actual fun formatIsoToTodayTime(isoString: String): String {
    val locale = NSLocale.currentLocale
    val date = parseIsoDate(isoString, locale) ?: return isoString
    val now = NSDate()

    val relativeDay = getRelativeDayLabel(date, now, locale)
    val timeStr = formatTime(date, locale)

    return "$relativeDay, $timeStr"
}

private fun makeFormatter(
    pattern: String,
    locale: NSLocale,
    timeZone: NSTimeZone = NSTimeZone.localTimeZone
): NSDateFormatter {
    return NSDateFormatter().apply {
        dateFormat = pattern
        this.locale = locale
        this.timeZone = timeZone
    }
}

private fun parseIsoDate(isoString: String, locale: NSLocale): NSDate? {
    val mainFormatter =
        makeFormatter("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", locale)
    val fallbackFormatter =
        makeFormatter("yyyy-MM-dd'T'HH:mm:ssZZZZZ", NSLocale(localeIdentifier = "en_US_POSIX"))
    return mainFormatter.dateFromString(isoString) ?: fallbackFormatter.dateFromString(isoString)
}

private fun getRelativeDayLabel(date: NSDate, now: NSDate, locale: NSLocale): String {
    val calendar = NSCalendar.currentCalendar
    val dayDiff = calendar.components(
        NSCalendarUnitDay,
        fromDate = date,
        toDate = now,
        options = 0u
    ).day

    return when (dayDiff) {
        0L -> if (locale.languageCode == "ar") "اليوم" else "Today"
        -1L -> if (locale.languageCode == "ar") "أمس" else "Yesterday"
        in -6..-2 -> makeFormatter("EEEE", locale).stringFromDate(date)
        else -> makeFormatter("MMM dd, yyyy", locale).stringFromDate(date)
    }
}

private fun formatTime(date: NSDate, locale: NSLocale): String {
    return makeFormatter("hh:mm a", locale).stringFromDate(date)
}

actual fun getCurrentLocale(): String {
    return NSLocale.currentLocale.languageCode
}

fun formateDateDayMonth(dateIso: String): String {
    val locale = NSLocale.currentLocale
    val date = parseIsoDate(dateIso, locale) ?: return dateIso
    return makeFormatter("dd MMM, yyyy", locale).stringFromDate(date)
}