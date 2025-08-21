package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.model.WeeklyProgress
import kotlinx.datetime.LocalDate

interface HomeRepository {
    suspend fun getWeeklyProgress(
        startDate: LocalDate,
        endDate: LocalDate
    ): WeeklyProgress
}