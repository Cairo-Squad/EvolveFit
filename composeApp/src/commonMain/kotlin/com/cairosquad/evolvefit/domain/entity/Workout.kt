package com.cairosquad.evolvefit.domain.entity

import com.cairosquad.evolvefit.domain.model.FocusArea

data class Workout(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val level: WorkoutLevel,
    val estimatedTimeInSeconds: Int,
    val exercises: List<Exercise>,
) {
    enum class WorkoutLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }
}

data class WorkoutSuggested(
    val id: String,
    val name: String,
    val durationSeconds: Int,
    val imageUrl: String,
    val focusArea: List<FocusArea>
)