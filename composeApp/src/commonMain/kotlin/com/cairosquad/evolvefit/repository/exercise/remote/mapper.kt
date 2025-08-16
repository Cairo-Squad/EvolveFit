package com.cairosquad.evolvefit.repository.exercise.remote

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseDto
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseResponseDto

fun Exercise.toDto(): ExerciseDto {
    return ExerciseDto(
        name = this.name,
        gymEquipments = listOf(this.equipment.id),
        instructions = this.instructions,
        focusArea = this.focusAreas.map { it.name },
        exerciseType = when (specification) {
            is Exercise.Specification.Reps -> "REPS"
            is Exercise.Specification.Time -> "DURATION"
        },
        reps = (specification as? Exercise.Specification.Reps)?.reps ?: 0,
        durationSeconds = (specification as? Exercise.Specification.Time)?.timeInSeconds ?: 0,
    )
}

fun ExerciseDto.toDomain(
): Exercise {
    val spec = when (exerciseType) {
        "REPS" -> Exercise.Specification.Reps(reps ?: 0)
        "DURATION" -> Exercise.Specification.Time(durationSeconds ?: 0)
        else -> throw IllegalArgumentException("Invalid exerciseType: $exerciseType")
    }

    val equipment = gymEquipments.firstOrNull()?.let { Equipment(it, "Unknown") }
        ?: Equipment(0, "Unknown")

    val focusAreas = focusArea.map { FocusArea.valueOf(it.uppercase()) }.toSet()

    return Exercise(
        id = "",
        name = name ?: "",
        instructions = instructions,
        imageUrls = emptyList(),
        equipment = equipment,
        specification = spec,
        focusAreas = focusAreas,
        estimatedTimeInSeconds = durationSeconds ?: 0
    )
}


fun ExerciseResponseDto.toDomain(): Exercise {
    val spec = when (exerciseType) {
        "REPS" -> Exercise.Specification.Reps(reps ?: 0)
        "DURATION" -> Exercise.Specification.Time(durationSeconds ?: 0)
        else -> throw IllegalArgumentException("Invalid exerciseType: $exerciseType")
    }

    val equipment = gymEquipments.firstOrNull()?.let { Equipment(it.id, "Unknown") }
        ?: Equipment(0, "Unknown")

    val focusAreas = focusArea.map { FocusArea.valueOf(it.uppercase()) }.toSet()

    return Exercise(
        id = id,
        name = name,
        specification = spec,
        imageUrls = images,
        equipment = equipment,
        focusAreas = focusAreas,
        instructions = instructions,
        estimatedTimeInSeconds = durationSeconds ?: 0
    )
}