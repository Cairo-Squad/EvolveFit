package com.cairosquad.evolvefit.domain.entity

data class Workout(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val level: WorkoutLevel,
    val estimatedTimeSeconds: Int,
    val exercises: List<Exercise>,
) {
    enum class WorkoutLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }
}