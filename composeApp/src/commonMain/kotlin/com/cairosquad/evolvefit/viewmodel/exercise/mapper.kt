package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.entity.FocusArea
import com.cairosquad.evolvefit.domain.entity.MeasurementType
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.Equipment

fun CreateExerciseState.toDomainExercise(): Exercise {
    return Exercise(
        name = this.name,
        imageUrl = this.image?.toString(),
        equipmentIds = this.selectedEquipments,
        measurementType = when (this.measurementType) {
            CreateExerciseState.MeasurementType.DURATION -> MeasurementType.DURATION
            CreateExerciseState.MeasurementType.REPS -> MeasurementType.REPS
        },
        measurementValue = this.measurementInputValue ?: 0,
        focusAreas = this.selectedFocusAreas.map { it.toDomainFocusArea() }.toSet(),
        description = this.description
    )
}

private fun CreateExerciseState.FocusArea.toDomainFocusArea(): FocusArea {
    return when (this) {
        CreateExerciseState.FocusArea.Quadriceps -> FocusArea.Quadriceps
        CreateExerciseState.FocusArea.Abs -> FocusArea.Abs
        CreateExerciseState.FocusArea.Calves -> FocusArea.Calves
        CreateExerciseState.FocusArea.LowerBack -> FocusArea.LowerBack
        CreateExerciseState.FocusArea.Core -> FocusArea.Core
        CreateExerciseState.FocusArea.Shoulders -> FocusArea.Shoulders
    }
}

fun Equipment.toEquipment(): Equipment {
    return Equipment(
        toolId = this.id,
        toolName = this.name
    )
}

fun List<Equipment>.toEquipments(): List<Equipment> {
    return this.map { it.toEquipment() }
}