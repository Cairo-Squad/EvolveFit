package com.cairosquad.evolvefit.domain.entity

import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.domain.model.WeekDay
import kotlinx.datetime.LocalDate

data class Profile(
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val gender: Gender,
    val preferredMeasurementStandard: MeasurementStandard,
    val height: Float,
    val weight: Float,
    val goal: FitnessGoal,
    val imageUrl:String,
    val equipments: Set<Equipment>,
    val workoutDays: Set<WeekDay>
    ) {
    enum class FitnessGoal {
        LOSE_WEIGHT,
        GAIN_WEIGHT,
        STAY_IN_SHAPE
    }
    enum class Gender {
        MALE,
        FEMALE
    }
}