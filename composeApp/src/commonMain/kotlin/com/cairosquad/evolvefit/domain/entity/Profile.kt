package com.cairosquad.evolvefit.domain.entity

import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import kotlinx.datetime.LocalDate

data class Profile(
    val name: String,
    val email: String,
    val dateOfBirth: LocalDate,
    val gender: Gender,
    val preferredMeasurementStandard: MeasurementStandard,
    val preferredLanguage: Language,
    val height: Float,
    val weight: Float,
    val goal: FitnessGoal,
    val imageUrl:String,

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