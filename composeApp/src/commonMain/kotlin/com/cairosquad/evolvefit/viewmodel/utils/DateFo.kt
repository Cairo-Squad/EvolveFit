package com.cairosquad.evolvefit.viewmodel.utils
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getTodayDate(): String {
    val currentMoment = Clock.System.now()
    val dateTimeInLocalZone = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
    return dateTimeInLocalZone.date.toString()
}
fun getCurrentIsoDateTime(): String {
    val currentMoment = Clock.System.now()
    val localDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
    return localDateTime.toString()
}