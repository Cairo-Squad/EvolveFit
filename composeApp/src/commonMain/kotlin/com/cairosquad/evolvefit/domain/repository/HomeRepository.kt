package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.usecase.home.model.User
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress

interface HomeRepository {
    suspend fun getUser(): User
    suspend fun getWeeklyProgress(): WeeklyProgress
}