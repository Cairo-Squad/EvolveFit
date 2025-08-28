package com.cairosquad.evolvefit.repository.home.data_source.remote.dto

import com.cairosquad.evolvefit.repository.utils.localDateTimeToLocalDate
import com.cairosquad.evolvefit.repository.utils.parseIsoDateStringToLocalDateTime
import com.cairosquad.evolvefit.repository.utils.parseIsoStringToLocalDateTime
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

fun createWorkoutDatesMap(
    startDate: LocalDate,
    endDate: LocalDate,
    workoutDates: List<String>
): Map<Int, Boolean> {
    val workoutSet =
        workoutDates.toSet().map { localDateTimeToLocalDate(parseIsoDateStringToLocalDateTime(it)) }
    val result = mutableMapOf<Int, Boolean>()

    var current = startDate
    while (current <= endDate) {
        result[current.dayOfMonth] = current.toString() in workoutSet.map { it.toString() }
        current = current.plus(1, DateTimeUnit.DAY)
    }
    return result
}