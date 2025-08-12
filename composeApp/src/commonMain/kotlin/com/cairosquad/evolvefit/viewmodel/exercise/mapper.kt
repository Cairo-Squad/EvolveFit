package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.model.FocusArea

fun CreateExerciseState.toDomainExercise(): Exercise {
    return Exercise(
        name = this.name,
        imageUrls = listOf(this.image?.toString() ?: ""), // todo
        equipment = this.selectedEquipment.toDomain(),
        specification = when (measurementType) {
            CreateExerciseState.MeasurementType.DURATION -> Exercise.Specification.Time(
                measurementInputValue ?: 0
            )

            CreateExerciseState.MeasurementType.REPS -> Exercise.Specification.Reps(
                measurementInputValue ?: 0
            )
        },
        focusAreas = this.selectedFocusAreas.map { it.toDomain() }.toSet(),
        instructions = this.description.split("\n"),
        id = "",
        estimatedTimeInSeconds = 60 // todo
    )
}

private fun CreateExerciseState.FocusArea.toDomain(): FocusArea {
    return when (this) {
        CreateExerciseState.FocusArea.Quadriceps -> FocusArea.QUADRICEPS
        CreateExerciseState.FocusArea.Abs -> FocusArea.ABS
        CreateExerciseState.FocusArea.Calves -> FocusArea.CALVES
        CreateExerciseState.FocusArea.LowerBack -> FocusArea.LOWER_BACK
        CreateExerciseState.FocusArea.Core -> FocusArea.CORE
        CreateExerciseState.FocusArea.Shoulders -> FocusArea.SHOULDERS
    }
}

fun CreateExerciseState.EquipmentUiState.toDomain(): Equipment {
    return Equipment(
        id = this.id,
        name = this.name
    )
}

fun Equipment.toUiState(): CreateExerciseState.EquipmentUiState{
    return CreateExerciseState.EquipmentUiState(
        id = this.id,
        name = this.name
    )
}

fun List<CreateExerciseState.EquipmentUiState>.toDomainList(): List<Equipment> {
    return this.map { it.toDomain() }
}