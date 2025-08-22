package com.cairosquad.evolvefit.viewmodel.play_workout

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.focus_abs
import evolvefit.composeapp.generated.resources.focus_calves
import evolvefit.composeapp.generated.resources.focus_core
import evolvefit.composeapp.generated.resources.focus_lower_back
import evolvefit.composeapp.generated.resources.focus_quadriceps
import evolvefit.composeapp.generated.resources.focus_shoulders
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

fun Workout.toUiState(): PlayWorkoutScreenState.WorkoutUiState {
    return PlayWorkoutScreenState.WorkoutUiState(
        id = id,
        name = name,
        imageUrl = imageUrl,
        exercises = exercises.map { it.toUiState() },
        level = PlayWorkoutScreenState.WorkoutLevelUiState.valueOf(level.name)

    )
}

fun Exercise.toUiState(): PlayWorkoutScreenState.ExerciseUiState {
    return PlayWorkoutScreenState.ExerciseUiState(
        id = id,
        name = name,
        imageUrls = imageUrls,
        exerciseSpec = when (this.specification) {
            is Exercise.Specification.Reps -> {
                PlayWorkoutScreenState.ExerciseSpecUiState.Reps(this.specification.reps)
            }
            is Exercise.Specification.Time -> {
                PlayWorkoutScreenState.ExerciseSpecUiState.Time(this.specification.timeInSeconds)
            }
        },
        focusAreas = focusAreas.map { it.toStringRes() }.toSet(),
        instructions = instructions,
        equipment = equipment.name
    )
}

private fun FocusArea.toStringRes(): StringResource {
    return when (this) {
        FocusArea.CORE -> Res.string.focus_core
        FocusArea.SHOULDERS -> Res.string.focus_shoulders
        FocusArea.BACK -> Res.string.focus_lower_back
        FocusArea.LEGS -> Res.string.focus_calves
        FocusArea.ARMS -> Res.string.focus_abs
        FocusArea.CHEST -> Res.string.focus_quadriceps
    }
}