package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.model.User
import com.cairosquad.evolvefit.domain.model.WeeklyProgress

interface HomeRepository {
    suspend fun getUser(): User
    suspend fun getWeeklyProgress(): WeeklyProgress
}