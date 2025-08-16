package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import io.github.vinceglb.filekit.path

fun CreateExerciseState.toDomainExercise(): Exercise {
    val timeOrReps = measurementInputValue.toIntOrNull() ?: 0

    val imageUrl = when (val img = this.image) {
        is UiImage.ImageUrl -> img.url
        is UiImage.ImageResource -> ""
        is UiImage.ImageFile -> img.platformFile.path
        null -> ""
    }
    return Exercise(
        name = this.name,
        imageUrls = listOfNotNull(imageUrl.takeIf { it.isNotBlank() }),
        equipment = this.selectedEquipment.toDomain(),
        specification = when (measurementType) {
            CreateExerciseState.MeasurementType.DURATION -> Exercise.Specification.Time(
                measurementInputValue.toIntOrNull() ?: 0
            )

            CreateExerciseState.MeasurementType.REPS -> Exercise.Specification.Reps(
                measurementInputValue.toInt()
            )
        },
        focusAreas = this.selectedFocusAreas.map { it.toDomain() }.toSet(),
        instructions = this.description.split("\n"),
        id = "",
        estimatedTimeInSeconds = when (measurementType) {
            CreateExerciseState.MeasurementType.DURATION -> timeOrReps
            CreateExerciseState.MeasurementType.REPS -> timeOrReps * 3
        }
    )
}


private fun CreateExerciseState.FocusArea.toDomain(): FocusArea {
    return when (this) {
        CreateExerciseState.FocusArea.SHOULDERS -> FocusArea.SHOULDERS
        CreateExerciseState.FocusArea.CORE -> FocusArea.CORE
        CreateExerciseState.FocusArea.ARMS -> FocusArea.ARMS
        CreateExerciseState.FocusArea.BACK -> FocusArea.BACK
        CreateExerciseState.FocusArea.LEGS -> FocusArea.LEGS
        CreateExerciseState.FocusArea.CHEST -> FocusArea.CHEST
    }
}

fun CreateExerciseState.EquipmentUiState.toDomain(): Equipment {
    return Equipment(
        id = this.id,
        name = this.name
    )
}

fun Equipment.toUiState(): CreateExerciseState.EquipmentUiState {
    return CreateExerciseState.EquipmentUiState(
        id = this.id,
        name = this.name
    )
}

fun List<CreateExerciseState.EquipmentUiState>.toDomainList(): List<Equipment> {
    return this.map { it.toDomain() }
}

//fun Exercise.toUiExercise(): CreateExerciseState.ExerciseUiModel {
//    val measurementType = when (specification) {
//        is Exercise.Specification.Time -> CreateExerciseState.MeasurementType.DURATION
//        is Exercise.Specification.Reps -> CreateExerciseState.MeasurementType.REPS
//    }
//
//    val measurementValue = when (specification) {
//        is Exercise.Specification.Time -> specification.timeInSeconds
//        is Exercise.Specification.Reps -> specification.reps
//    }
//
//    return CreateExerciseState.ExerciseUiModel(
//        id = id.toIntOrNull() ?: 0,
//        name = name,
//        image = imageUrls.firstOrNull()?.let { UiImage.ImageUrl(it) },
//        equipments = listOf(equipment.name),
//        focusAreas = focusAreas.toList(),
//        measurementType = measurementType,
//        measurementValue = measurementValue,
//        description = instructions.joinToString("\n")
//    )
