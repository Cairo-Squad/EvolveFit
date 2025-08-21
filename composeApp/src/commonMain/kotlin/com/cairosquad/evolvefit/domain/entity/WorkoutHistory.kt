package com.cairosquad.evolvefit.domain.entity

data class WorkoutHistory(
    val name: String,
    val imageUrl: String,
    val createdAt: String,
    val exercisesCount: Int,
    val durationInSeconds: Long,
    val level: WorkoutLevel,
) {
    enum class WorkoutLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }
}
