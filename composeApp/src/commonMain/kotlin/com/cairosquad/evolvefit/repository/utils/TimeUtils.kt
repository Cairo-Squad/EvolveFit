package com.cairosquad.evolvefit.repository.utils

import kotlinx.datetime.LocalDate

fun LocalDate.toRemoteDto(): String {
    val day = this.dayOfMonth.toString().padStart(2, '0')
    val month = (this.month.ordinal + 1).toString().padStart(2, '0')
    return "${this.year}-$month-$day"
}