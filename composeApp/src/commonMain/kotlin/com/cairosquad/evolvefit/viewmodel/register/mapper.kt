package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.entity.Profile.FitnessGoal
import com.cairosquad.evolvefit.domain.entity.Profile.Gender
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.domain.model.WeekDay

fun List<Long>.toToolsDomain(state: RegisterScreenState): List<Equipment> {
    return this.mapNotNull { selectedId ->
        state.availableEquipments.find { it.toolId == selectedId }?.let {
            Equipment(id = selectedId, name = it.toolName)
        }
    }
}

fun RegisterScreenState.WorkoutDay.toDomain(): WeekDay {
    return when (this) {
        RegisterScreenState.WorkoutDay.MONDAY -> WeekDay.MONDAY
        RegisterScreenState.WorkoutDay.TUESDAY -> WeekDay.TUESDAY
        RegisterScreenState.WorkoutDay.WEDNESDAY -> WeekDay.WEDNESDAY
        RegisterScreenState.WorkoutDay.THURSDAY -> WeekDay.THURSDAY
        RegisterScreenState.WorkoutDay.FRIDAY -> WeekDay.FRIDAY
        RegisterScreenState.WorkoutDay.SATURDAY -> WeekDay.SATURDAY
        RegisterScreenState.WorkoutDay.SUNDAY -> WeekDay.SUNDAY
    }
}

fun RegisterScreenState.Goal.toDomain(): FitnessGoal {
    return when (this) {
        RegisterScreenState.Goal.LoseWeight -> FitnessGoal.LOSE_WEIGHT
        RegisterScreenState.Goal.GainWeight -> FitnessGoal.GAIN_WEIGHT
        RegisterScreenState.Goal.StayInShape -> FitnessGoal.STAY_IN_SHAPE
    }
}

fun RegisterScreenState.MeasurementStandard.toDomain(): MeasurementStandard {
    return when (this) {
        RegisterScreenState.MeasurementStandard.Metric -> MeasurementStandard.METRIC
        RegisterScreenState.MeasurementStandard.Imperial -> MeasurementStandard.IMPERIAL
    }
}

fun RegisterScreenState.Gender.toDomain(): Gender {
    return when (this) {
        RegisterScreenState.Gender.Male -> Gender.MALE
        RegisterScreenState.Gender.Female -> Gender.FEMALE
    }
}

fun RegisterScreenState.toDomain(): Profile {
    return Profile(
        name = this.userNameInput,
        email = this.userEmailInput,
        gender = this.selectedGender?.toDomain() ?: Profile.Gender.MALE,
        dateOfBirth = this.dateOfBirthInput,
        preferredMeasurementStandard = this.selectedMeasurementStandard?.toDomain() ?: MeasurementStandard.METRIC,
        goal = this.selectedGoal?.toDomain() ?: FitnessGoal.STAY_IN_SHAPE,
        height = this.selectedHeight,
        weight = this.selectedWeight,
        equipments = this.selectedEquipments.toToolsDomain(this),
        workoutDays = this.selectedWorkoutDays.map { it.toDomain() }
    )
}

