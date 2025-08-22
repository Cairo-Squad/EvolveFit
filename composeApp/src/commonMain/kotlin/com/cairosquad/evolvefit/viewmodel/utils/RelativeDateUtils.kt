package com.cairosquad.evolvefit.viewmodel.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

fun getDayHeader(date: LocalDate): String {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val locale = getCurrentLocale()

    val daysDiff = today.toEpochDays() - date.toEpochDays()
    val weeksDiff = daysDiff / 7
    val monthsDiff = (today.year - date.year) * 12 + (today.monthNumber - date.monthNumber)
    val yearsDiff = today.year - date.year

    return when {
        daysDiff == 0 -> tr(locale, "اليوم", "Today")
        daysDiff == 1 -> tr(locale, "أمس", "Yesterday")
        daysDiff in 2..6 -> formatDayName(date, locale)
        weeksDiff in 1..3 -> formatWeeks(weeksDiff, locale)
        monthsDiff in 1..11 -> formatMonths(monthsDiff, locale)
        yearsDiff >= 1 -> formatYears(yearsDiff, locale)
        else -> "${date.dayOfMonth} ${date.month.name.lowercase().take(3)} ${date.year}"
    }
}

private fun tr(locale: String, ar: String, en: String): String {
    return if (locale.startsWith("ar")) ar else en
}

private fun formatDayName(date: LocalDate, locale: String): String {
    return if (locale.startsWith("ar")) {
        when (date.dayOfWeek) {
            DayOfWeek.MONDAY -> "الاثنين"
            DayOfWeek.TUESDAY -> "الثلاثاء"
            DayOfWeek.WEDNESDAY -> "الأربعاء"
            DayOfWeek.THURSDAY -> "الخميس"
            DayOfWeek.FRIDAY -> "الجمعة"
            DayOfWeek.SATURDAY -> "السبت"
            DayOfWeek.SUNDAY -> "الأحد"
            else -> "?"
        }
    } else {
        date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
    }
}

private fun formatWeeks(weeks: Int, locale: String): String {
    return if (locale.startsWith("ar")) {
        when (weeks) {
            1 -> "منذ أسبوع"
            2 -> "منذ أسبوعين"
            else -> "منذ $weeks أسابيع"
        }
    } else {
        when (weeks) {
            1 -> "Week ago"
            2 -> "2 Weeks ago"
            else -> "$weeks Weeks ago"
        }
    }
}

private fun formatMonths(months: Int, locale: String): String {
    return if (locale.startsWith("ar")) {
        when (months) {
            1 -> "منذ شهر"
            2 -> "منذ شهرين"
            else -> "منذ $months أشهر"
        }
    } else {
        when (months) {
            1 -> "Month ago"
            else -> "$months Months ago"
        }
    }
}

private fun formatYears(years: Int, locale: String): String {
    return if (locale.startsWith("ar")) {
        when (years) {
            1 -> "منذ سنة"
            2 -> "منذ سنتين"
            else -> "منذ $years سنوات"
        }
    } else {
        when (years) {
            1 -> "Year ago"
            else -> "$years Years ago"
        }
    }
}