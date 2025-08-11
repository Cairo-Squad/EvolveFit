package com.cairosquad.evolvefit.entity

data class Workout(
    val id: String = "",
    val name: String,
    val level: String,
    val description: String,
    val imageUrl: String?,
    val exercisesId: List<Long>
)
data class Exercise(
    val id: String,
    val name: String,
    val imageUrl: String,
    val exerciseDuration: ExerciseDuration
)

sealed class ExerciseDuration {
    data class Time(val seconds: Int) : ExerciseDuration()
    data class RepsSets(val reps: Int, val sets: Int) : ExerciseDuration()
}
