package com.cairosquad.evolvefit.entity


data class Workout(
    val id: String,
    val name: String,
    val imageUrl: String,
    val exercises: List<Exercise>,
    val level: WorkoutLevel,
) {
    enum class WorkoutLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }
}
