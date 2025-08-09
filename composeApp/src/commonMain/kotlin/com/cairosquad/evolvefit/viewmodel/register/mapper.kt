package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.entity.FitnessGoal
import com.cairosquad.evolvefit.entity.Gender
import com.cairosquad.evolvefit.entity.MeasurementUnit
import com.cairosquad.evolvefit.entity.Tool
import com.cairosquad.evolvefit.entity.WorkoutDay

fun List<Long>.toToolsDomain(state: RegisterScreenState): List<Tool> {
    return this.mapNotNull { selectedId ->
        state.availableEquipments.find { it.toolId == selectedId }?.let {
            Tool(id = selectedId, name = it.toolName)
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
