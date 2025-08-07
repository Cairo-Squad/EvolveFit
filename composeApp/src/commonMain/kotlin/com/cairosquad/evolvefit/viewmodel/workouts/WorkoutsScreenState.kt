package com.cairosquad.evolvefit.viewmodel.workouts

data class WorkoutsScreenState(
    val selectedBodyPart: String = "All",
    val allWorkouts: List<WorkoutUiModel> = emptyList()
) {
    data class WorkoutUiModel(
        val id: Long,
        val title: String,
        val duration: String,
        val bodyPart: String
    )
}