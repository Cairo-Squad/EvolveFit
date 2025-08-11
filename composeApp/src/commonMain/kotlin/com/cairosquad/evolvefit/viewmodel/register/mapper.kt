package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.FitnessGoal
import com.cairosquad.evolvefit.domain.entity.Gender
import com.cairosquad.evolvefit.domain.entity.MeasurementUnit
import com.cairosquad.evolvefit.domain.entity.User
import com.cairosquad.evolvefit.domain.entity.WorkoutDay

fun List<Long>.toToolsDomain(state: RegisterScreenState): List<Equipment> {
    return this.mapNotNull { selectedId ->
        state.availableEquipments.find { it.toolId == selectedId }?.let {
            Equipment(id = selectedId, name = it.toolName)
        }
    }
}

fun RegisterScreenState.WorkoutDay.toDomain(): WorkoutDay {
    return when (this) {
        RegisterScreenState.WorkoutDay.MONDAY -> WorkoutDay.MONDAY
        RegisterScreenState.WorkoutDay.TUESDAY -> WorkoutDay.TUESDAY
        RegisterScreenState.WorkoutDay.WEDNESDAY -> WorkoutDay.WEDNESDAY
        RegisterScreenState.WorkoutDay.THURSDAY -> WorkoutDay.THURSDAY
        RegisterScreenState.WorkoutDay.FRIDAY -> WorkoutDay.FRIDAY
        RegisterScreenState.WorkoutDay.SATURDAY -> WorkoutDay.SATURDAY
        RegisterScreenState.WorkoutDay.SUNDAY -> WorkoutDay.SUNDAY
    }
}

fun RegisterScreenState.Goal.toDomain(): FitnessGoal {
    return when (this) {
        RegisterScreenState.Goal.LoseWeight -> FitnessGoal.LOSE_WEIGHT
        RegisterScreenState.Goal.GainWeight -> FitnessGoal.GAIN_WEIGHT
        RegisterScreenState.Goal.StayInShape -> FitnessGoal.STAY_IN_SHAPE
    }
}

fun RegisterScreenState.MeasurementStandard.toDomain(): MeasurementUnit {
    return when (this) {
        RegisterScreenState.MeasurementStandard.Metric -> MeasurementUnit.METRIC
        RegisterScreenState.MeasurementStandard.Imperial -> MeasurementUnit.IMPERIAL
    }
}

fun RegisterScreenState.Gender.toDomain(): Gender {
    return when (this) {
        RegisterScreenState.Gender.Male -> Gender.MALE
        RegisterScreenState.Gender.Female -> Gender.FEMALE
    }
}

fun RegisterScreenState.toDomain(): User {
    return User(
        name = this.userNameInput,
        email = this.userEmailInput,
        password = this.userPasswordInput,
        gender = this.selectedGender?.toDomain() ?: Gender.MALE,
        dateOfBirth = this.dateOfBirthInput,
        unit = this.selectedMeasurementStandard?.toDomain() ?: MeasurementUnit.METRIC,
        goal = this.selectedGoal?.toDomain() ?: FitnessGoal.STAY_IN_SHAPE,
        height = this.selectedHeight,
        weight = this.selectedWeight,
        equipments = this.selectedEquipments.toToolsDomain(this),
        workoutDays = this.selectedWorkoutDays.map { it.toDomain() }
    )
}

