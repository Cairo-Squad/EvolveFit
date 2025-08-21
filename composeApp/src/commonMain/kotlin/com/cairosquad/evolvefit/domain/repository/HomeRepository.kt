package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.model.User
import com.cairosquad.evolvefit.domain.model.WeeklyProgress
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress
import kotlinx.datetime.LocalDate

interface HomeRepository {
    suspend fun getWeeklyProgress(
        startDate: LocalDate,
        endDate: LocalDate
    ): WeeklyProgress
}