package com.cairosquad.evolvefit.repository.profile

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.ProfileRepository
import kotlinx.datetime.LocalDate

class ProfileRepositoryImpl : ProfileRepository {
    override suspend fun getProfile(): Profile{
        return Profile(
            name = "John Doe",
            email = "john.doe@example.com",
            dateOfBirth = LocalDate(1995, 5, 20),
            gender = Profile.Gender.MALE,
            preferredMeasurementStandard = MeasurementStandard.METRIC,
            preferredLanguage = Language.ENGLISH,
            height = 180f,
            weight = 75f,
            goal = Profile.FitnessGoal.GAIN_WEIGHT,
            imageUrl = "https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg"
        )
    }

    override suspend fun getUserWorkoutDays(): Set<WeekDay>{
        return emptySet()
    }

    override suspend fun editUserWorkoutDays(weekDays: Set<WeekDay>){

    }

}