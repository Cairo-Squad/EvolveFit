package com.cairosquad.evolvefit.viewmodel.createWorkOut

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.Workout.WorkoutLevel
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState
import com.cairosquad.evolvefit.viewmodel.exercise.toDomain
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsScreenState
import io.github.vinceglb.filekit.path

fun CreateWorkOutScreenState.toDomainWorkout(exercises: List<CreateWorkOutScreenState.ExerciseUiState> = emptyList()): Workout {
    val imageUrl = when (val img = this.image) {
        is UiImage.ImageUrl -> img.url
        is UiImage.ImageResource -> ""
        is UiImage.ImageFile -> img.platformFile.path
        null -> ""
    }
    return Workout(
        id = "",
        name = name,
        description = description,
        imageUrl = listOfNotNull(imageUrl.takeIf { it.isNotBlank() }).toString(),
        level = goal.toDomain(),
        estimatedTimeInSeconds = exercises.sumOf {
            when (val t = it.type) {
                is CreateWorkOutScreenState.ExerciseType.Duration -> t.seconds
                is CreateWorkOutScreenState.ExerciseType.Reps -> t.count
            }
        },
        exercises = exercises.map { it.toDomain() }
    )
}

fun CreateWorkOutScreenState.ExerciseUiState.toDomain(): Exercise {
    val spec = when (val t = type) {
        is CreateWorkOutScreenState.ExerciseType.Reps -> Exercise.Specification.Reps(t.count)
        is CreateWorkOutScreenState.ExerciseType.Duration -> Exercise.Specification.Time(t.seconds)
    }

    return Exercise(
        id = id,
        name = name,
        instructions = instructions,
        imageUrls = images,
        equipment = equipment.toDomain(),
        focusAreas =focusAreas.map { it.toDomain() }.toSet(),
        specification = spec,
        estimatedTimeInSeconds = when (spec) {
            is Exercise.Specification.Time -> spec.timeInSeconds
            is Exercise.Specification.Reps -> spec.reps
        }
    )
}

private fun CreateWorkOutScreenState.FocusArea.toDomain(): FocusArea {
    return when (this) {
        CreateWorkOutScreenState.FocusArea.SHOULDERS -> FocusArea.SHOULDERS
        CreateWorkOutScreenState.FocusArea.CORE -> FocusArea.CORE
        CreateWorkOutScreenState.FocusArea.ARMS -> FocusArea.ARMS
        CreateWorkOutScreenState.FocusArea.BACK -> FocusArea.BACK
        CreateWorkOutScreenState.FocusArea.LEGS -> FocusArea.LEGS
        CreateWorkOutScreenState.FocusArea.CHEST -> FocusArea.CHEST
    }
}

private fun CreateWorkOutScreenState.WorkoutLevel.toDomain(): WorkoutLevel{
    return when (this) {
        CreateWorkOutScreenState.WorkoutLevel.BEGINNER -> WorkoutLevel.BEGINNER
        CreateWorkOutScreenState.WorkoutLevel.INTERMEDIATE -> WorkoutLevel.INTERMEDIATE
        CreateWorkOutScreenState.WorkoutLevel.ADVANCED -> WorkoutLevel.ADVANCED
    }

}
fun CreateWorkOutScreenState.EquipmentUiState.toDomain(): Equipment {
    return Equipment(
        id = this.id,
        name = this.name
    )
}

fun Exercise.toUiState(): CreateWorkOutScreenState.ExerciseUiState {
    return CreateWorkOutScreenState.ExerciseUiState(
        id = id,
        name = name,
        instructions = instructions,
        images = imageUrls,
        type = when (specification) {
            is Exercise.Specification.Reps ->
                CreateWorkOutScreenState.ExerciseType.Reps(specification.reps)

            is Exercise.Specification.Time ->
                CreateWorkOutScreenState.ExerciseType.Duration(specification.timeInSeconds)
        },
        equipment = equipment.toUiState(),
        focusAreas = focusAreas.map { CreateWorkOutScreenState.FocusArea.valueOf(it.name) }
    )
}

fun Equipment.toUiState(): CreateWorkOutScreenState.EquipmentUiState {
    return CreateWorkOutScreenState.EquipmentUiState(
        id = this.id,
        name = this.name
    )
}

