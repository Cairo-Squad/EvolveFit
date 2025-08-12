package com.cairosquad.evolvefit.viewmodel.playWorkout

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.advanced
import evolvefit.composeapp.generated.resources.beginner
import evolvefit.composeapp.generated.resources.intermediate
import org.jetbrains.compose.resources.StringResource

data class PlayWorkoutScreenState(
    val stage: Stage = Stage.GET_READY,
    val workout: WorkoutUiState = WorkoutUiState(),
    val currentStep: Int = 1,
    val totalTimeMinutes: Int = 0,
    val haseCancelWorkoutClicked: Boolean = false,
    val showExerciseInfo: Boolean = false,
) {
    enum class Stage {
        GET_READY,
        PERFORM,
        REST,
        FINISH
    }

    data class WorkoutUiState(
        val id: String = "",
        val name: String = "",
        val imageUrl: String = "",
        val exercises: List<ExerciseUiState> = emptyList(),
        val level: WorkoutLevelUiState = WorkoutLevelUiState.BEGINNER
    )

    data class ExerciseUiState(
        val id: String = "",
        val name: String = "",
        val imageUrls: List<String> = emptyList(),
        val exerciseSpec: ExerciseSpecUiState = ExerciseSpecUiState.Reps(0)
    )

    sealed class ExerciseSpecUiState {
        class Reps(val reps: Int) : ExerciseSpecUiState()
        class Time(val timeInSeconds: Int) : ExerciseSpecUiState()
    }

    enum class WorkoutLevelUiState(val nameResId: StringResource) {
        BEGINNER(Res.string.beginner),
        INTERMEDIATE(Res.string.intermediate),
        ADVANCED(Res.string.advanced)
    }

}
