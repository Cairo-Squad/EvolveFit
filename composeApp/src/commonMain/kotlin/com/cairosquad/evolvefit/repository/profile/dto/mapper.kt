package com.cairosquad.evolvefit.repository.profile.dto

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.repository.equipment.remot.toDto

fun getFitnessGoal(goal: String): Profile.FitnessGoal {
    return when (goal) {
        "LOSE_WEIGHT" -> Profile.FitnessGoal.LOSE_WEIGHT
        "GAIN_WEIGHT" -> Profile.FitnessGoal.GAIN_WEIGHT
        "STAY_IN_SHAPE" -> Profile.FitnessGoal.STAY_IN_SHAPE
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

fun Profile.toDto(): ProfileGetDto {
    return ProfileGetDto(
        name = name,
        email = email,
        birthDate = dateOfBirth.toString(),
        gender = getGenderName(gender),
        imageUrl = imageUrl,
        measurementType = getMeasurementTypeName(preferredMeasurementStandard),
        height = height.toDouble(),
        weight = weight.toDouble(),
        goal = getFitnessGoalName(goal),
        gymEquipments = equipments.map{it.toDto()}.toList(),
        workoutDays = workoutDays.map { it.name }
    )
}
fun Profile.toProfilePostDto(): ProfilePostDto {
    val dto = ProfilePostDto(
        name = name,
        email = email,
        birthDate = dateOfBirth.toString(),
        gender = getGenderName(gender),
        imageUrl = imageUrl,
        measurementType = getMeasurementTypeName(preferredMeasurementStandard),
        height = height.toDouble(),
        weight = weight.toDouble(),
        goal = getFitnessGoalName(goal),
        gymEquipments = equipments.map { it.id },
        workoutDays = workoutDays.map { it.name }
    )
    return dto
}
fun getFitnessGoalName(goal: Profile.FitnessGoal): String =
    when (goal) {
        Profile.FitnessGoal.LOSE_WEIGHT -> "LOSE_WEIGHT"
        Profile.FitnessGoal.GAIN_WEIGHT -> "GAIN_WEIGHT"
        Profile.FitnessGoal.STAY_IN_SHAPE -> "STAY_IN_SHAPE"
    }

fun getGenderName(gender: Profile.Gender): String =
    when (gender) {
        Profile.Gender.MALE -> "MALE"
        Profile.Gender.FEMALE -> "FEMALE"
    }

fun getMeasurementTypeName(type: MeasurementStandard): String =
    when (type) {
        MeasurementStandard.METRIC -> "METRIC"
        MeasurementStandard.IMPERIAL -> "IMPERIAL"
    }