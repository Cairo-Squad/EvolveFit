package com.cairosquad.evolvefit.domain.usecase.profile

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.ProfileRepository

class ManageProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend fun getProfile(): Profile {
        return profileRepository.getProfile()
    }

    suspend fun getUserWorkoutDays(): Set<WeekDay> {
        return profileRepository.getUserWorkoutDays()
    }
    suspend fun editUserWorkoutDays(workoutDays : List<WeekDay>)
    {
        profileRepository.editUserWorkoutDays(workoutDays)
    }
}