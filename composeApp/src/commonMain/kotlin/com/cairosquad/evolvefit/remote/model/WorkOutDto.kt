package com.cairosquad.evolvefit.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDto(
    val id: String,
    val name: String,
    val image: String,
    val durationType: String,
    val seconds: Int? = null,
    val reps: Int? = null,
    val sets: Int? = null
)

@Serializable
data class WorkoutDto(
    val id: String? = null,
    val name: String,
    val goal: String,
    val description: String,
    val image: String? = null,
    val exercisesId: List<Long>
)
