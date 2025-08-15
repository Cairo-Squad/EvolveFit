package com.cairosquad.evolvefit.repository.profile.dto

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.MeasurementStandard

fun getFitnessGoal(goal: String): Profile.FitnessGoal {
    return when (goal) {
        "LOSE_WEIGHT" -> Profile.FitnessGoal.LOSE_WEIGHT
        "GAIN_WEIGHT" -> Profile.FitnessGoal.GAIN_WEIGHT
        "STAY_IN" -> Profile.FitnessGoal.STAY_IN_SHAPE
        else -> Profile.FitnessGoal.STAY_IN_SHAPE
    }
}

fun getGender(gender: String): Profile.Gender {
    return when (gender) {
        "MALE" -> Profile.Gender.MALE
        "FEMALE" -> Profile.Gender.FEMALE
        else -> throw IllegalArgumentException("There are only two genders! nothing called: $gender")
    }

}

fun getMeasurementStandard(measurementType: String): MeasurementStandard {
    return when (measurementType) {
        "METRIC" -> MeasurementStandard.METRIC
        "IMPERIAL" -> MeasurementStandard.IMPERIAL
        else -> throw IllegalArgumentException("Invalid measurement type: $measurementType")
    }

}