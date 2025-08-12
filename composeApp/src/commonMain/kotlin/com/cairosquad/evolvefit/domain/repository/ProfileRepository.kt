package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.WeekDay

interface ProfileRepository {
    suspend fun getProfile(): Profile
    suspend fun getUserWorkoutDays(): Set<WeekDay>
}