package com.cairosquad.evolvefit.viewmodel.workout_details

import org.jetbrains.compose.resources.StringResource

data class WorkoutDetailsScreenState(
    val isLoading: Boolean = false,
    val screenState: ScreenState = ScreenState.Loading,
    val workout: Workout = Workout(),
    val isShareClicked: Boolean = false,
    val isFavorite: Boolean = false,
    val isShareBottomSheetDismiss: Boolean = false,
    val snackBarMessageId: StringResource? = null
) {
    data class Workout(
        val workoutID: String = "",
        val workoutImage: String = "",
        val workoutTitle: String = "",
        val workoutDescription: String = "",
        val level: WorkoutLevel = WorkoutLevel.INTERMEDIATE,
        val estimatedTimeInSeconds: Int? = 0,
        val exercises: List<ExerciseUiState> = emptyList(),
        val selectedExercise: ExerciseUiState? = null,
    )
    sealed class ExerciseType {
        data class Duration(val seconds: Int) : ExerciseType()
        data class Reps(val count: Int) : ExerciseType()
    }

    data class ExerciseUiState(
        val name: String,
        val instructions: List<String>,
        val images: List<String>,
        val type: ExerciseType,
        val equipment: String,
        val focusAreas: List<FocusArea>
    )

    enum class WorkoutLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }

    enum class FocusArea {
        BACK,
        LEGS,
        SHOULDERS,
        ARMS,
        CORE,
        CHEST
    }

    enum class ScreenState {
        Loading,
        Success,
        Error
    }
}