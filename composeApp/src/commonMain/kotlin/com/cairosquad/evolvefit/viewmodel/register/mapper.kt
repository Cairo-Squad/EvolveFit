package com.cairosquad.evolvefit.viewmodel.register

import androidx.compose.runtime.Composable
import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Profile.FitnessGoal
import com.cairosquad.evolvefit.domain.entity.Profile.Gender
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource

fun RegisterScreenState.WeekDayUiState.toDomain(): WeekDay {
    return when (this) {
        RegisterScreenState.WeekDayUiState.MONDAY -> WeekDay.MONDAY
        RegisterScreenState.WeekDayUiState.TUESDAY -> WeekDay.TUESDAY
        RegisterScreenState.WeekDayUiState.WEDNESDAY -> WeekDay.WEDNESDAY
        RegisterScreenState.WeekDayUiState.THURSDAY -> WeekDay.THURSDAY
        RegisterScreenState.WeekDayUiState.FRIDAY -> WeekDay.FRIDAY
        RegisterScreenState.WeekDayUiState.SATURDAY -> WeekDay.SATURDAY
        RegisterScreenState.WeekDayUiState.SUNDAY -> WeekDay.SUNDAY
    }
}

fun RegisterScreenState.Goal?.toDomain(): FitnessGoal {
    return when (this) {
        RegisterScreenState.Goal.LoseWeight -> FitnessGoal.LOSE_WEIGHT
        RegisterScreenState.Goal.GainWeight -> FitnessGoal.GAIN_WEIGHT
        RegisterScreenState.Goal.StayInShape -> FitnessGoal.STAY_IN_SHAPE
        null -> FitnessGoal.LOSE_WEIGHT
    }
}

fun RegisterScreenState.MeasurementStandard?.toDomain(): MeasurementStandard {
    return when (this) {
        RegisterScreenState.MeasurementStandard.Metric -> MeasurementStandard.METRIC
        RegisterScreenState.MeasurementStandard.Imperial -> MeasurementStandard.IMPERIAL
        null -> MeasurementStandard.METRIC
    }
}

fun RegisterScreenState.Gender?.toDomain(): Gender {
    return when (this) {
        RegisterScreenState.Gender.Male -> Gender.MALE
        RegisterScreenState.Gender.Female -> Gender.FEMALE
        null -> Gender.MALE
    }
}
fun RegisterScreenState.Language?.toDomain(): Language {
    return when (this) {
        RegisterScreenState.Language.ARABIC -> Language.ARABIC
        RegisterScreenState.Language.ENGLISH-> Language.ENGLISH
        null -> Language.ARABIC
    }
}

fun RegisterScreenState.EquipmentUiState.toDomain(): Equipment {
    return Equipment(id = toolId, name = toolName)
}


fun dateUiStateToDomain(date: String): LocalDate {
    return when {
        Regex("""\d{4}-\d{2}-\d{2}""").matches(date) -> {
            val (y, m, d) = date.split("-").map { it.toInt() }
            LocalDate(y, m, d)
        }
        Regex("""\d{2}/\d{2}/\d{4}""").matches(date) -> {
            val (d, m, y) = date.split("/").map { it.toInt() }
            LocalDate(y, m, d)
        }
        else -> throw IllegalArgumentException("" )
    }
}

fun Equipment.toUiState(): RegisterScreenState.EquipmentUiState {
    return RegisterScreenState.EquipmentUiState(
        toolId = this.id,
        toolName = this.name
    )
}


