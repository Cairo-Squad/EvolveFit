package com.cairosquad.evolvefit.repository.workout.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlayedWorkoutDto(
    val workoutId: String,
    val durationSeconds: Int
)