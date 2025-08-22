package com.cairosquad.evolvefit.domain.usecase.home

import com.cairosquad.evolvefit.domain.repository.HomeRepository
import com.cairosquad.evolvefit.domain.model.WeeklyProgress
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class GetWeeklyProgressUseCase(
    private val homeRepository: HomeRepository
) {

    suspend fun getWeeklyProgress(): WeeklyProgress {
        return homeRepository.getWeeklyProgress(
            startDate = getCurrentDate().first,
            endDate = getCurrentDate().second
        )
    }

    private fun getCurrentDate(): Pair<LocalDate, LocalDate> {
        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val dayOfWeek = today.dayOfWeek

        val firstDay = today.minus(dayOfWeek.ordinal, DateTimeUnit.DAY) // Monday = 0
        val lastDay = firstDay.plus(6, DateTimeUnit.DAY)

        return firstDay to lastDay
    }
}