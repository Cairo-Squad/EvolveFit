package com.cairosquad.evolvefit.repository.home.data_source.remote.dto

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

fun createWorkoutDatesMap(
    startDate: LocalDate,
    endDate: LocalDate,
    workoutDates: List<String>
): Map<Int, Boolean> {
    val workoutSet = workoutDates.toSet()
    val result = mutableMapOf<Int, Boolean>()

    var current = startDate
    while (current <= endDate) {
        result[current.dayOfMonth] = current.toString() in workoutSet
        current = current.plus(1, DateTimeUnit.DAY)
    }

    return result
}