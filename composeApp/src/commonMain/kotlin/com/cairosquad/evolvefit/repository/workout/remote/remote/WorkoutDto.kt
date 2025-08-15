package com.cairosquad.evolvefit.repository.workout.remote.remote

data class FavoriteWorkoutDto(
	val workoutDto: List<WorkoutDto> = emptyList()
)

data class WorkoutDto(
	val durationSeconds: Int = 0,
	val imageUrl: String = "",
	val name: String = "",
	val workoutId: String = "",
	val focusArea: List<String> = emptyList()
)

