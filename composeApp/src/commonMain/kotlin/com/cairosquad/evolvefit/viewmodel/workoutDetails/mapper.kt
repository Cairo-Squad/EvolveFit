package com.cairosquad.evolvefit.viewmodel.workoutDetails

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.entity.Workout


fun Workout.toUiState(): WorkoutDetailsScreenState.Workout {
    return WorkoutDetailsScreenState.Workout(
        workoutID = id,
        workoutImage = imageUrl,
        workoutTitle = name,
        workoutDescription = description,
        exercises = exercises.map { it.toUiState() },
        level = WorkoutDetailsScreenState.WorkoutLevel.valueOf(level.name),
        estimatedTimeInSeconds = estimatedTimeInSeconds
    )
}

fun Exercise.toUiState(): WorkoutDetailsScreenState.ExerciseUiState {
    return WorkoutDetailsScreenState.ExerciseUiState(
        name = name,
        instructions = instructions,
        images = imageUrls,
        type = when (specification) {
            is Exercise.Specification.Reps ->
                WorkoutDetailsScreenState.ExerciseType.Reps(specification.reps)

            is Exercise.Specification.Time ->
                WorkoutDetailsScreenState.ExerciseType.Duration(specification.timeInSeconds)
        },
        equipment = equipment.name,
        focusAreas = focusAreas.map { WorkoutDetailsScreenState.FocusArea.valueOf(it.name) }
    )
}