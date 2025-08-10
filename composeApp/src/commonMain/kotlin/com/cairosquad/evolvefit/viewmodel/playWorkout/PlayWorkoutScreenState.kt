package com.cairosquad.evolvefit.viewmodel.playWorkout

data class PlayWorkoutScreenState(
    val stage: Stage = Stage.GET_READY,
    val workout: WorkoutUiState = WorkoutUiState(),
    val currentStep: Int = 1
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
        val description: String = "",
        val imageUrl: String = "",
        val exercises: List<ExerciseUiState> = emptyList()
    )

    data class ExerciseUiState(
        val id: String = "",
        val name: String = "",
        val description: String = "",
        val imageUrls: List<String> = emptyList(),
        val exerciseSpec: ExerciseSpecUiState = ExerciseSpecUiState.Reps(0)
    )

    sealed class ExerciseSpecUiState {
        class Reps(val reps: Int) : ExerciseSpecUiState()
        class Time(val timeSeconds: Int) : ExerciseSpecUiState()
    }

}
