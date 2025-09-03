package com.cairosquad.evolvefit.repository.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.atTime

fun LocalDate.toRemoteDto(): String {
    val day = this.dayOfMonth.toString().padStart(2, '0')
    val month = (this.month.ordinal + 1).toString().padStart(2, '0')
    return "${this.year}-$month-$day"
}

fun localDateToLocalDateTimeAtMidnight(date: LocalDate): LocalDateTime {
    return date.atTime(LocalTime(0, 0, 0))
}

fun localDateTimeToLocalDate(dateTime: LocalDateTime): LocalDate {
    return dateTime.date
}
fun parseIsoDateStringToLocalDateTime(isoDateString: String): LocalDateTime {
    val localDate = LocalDate.parse(isoDateString)
    return localDate.atTime(LocalTime(0, 0, 0))
}