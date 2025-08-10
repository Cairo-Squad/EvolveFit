package com.cairosquad.evolvefit.viewmodel.playWorkout

data class PlayWorkoutScreenState(
    val stage: Stage = Stage.GET_READY,
    val workout: WorkoutUiState = WorkoutUiState()
) {
    enum class Stage {
        GET_READY,
        PLAY,
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
    )

}
