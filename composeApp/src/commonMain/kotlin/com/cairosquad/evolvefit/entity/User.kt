package com.cairosquad.evolvefit.entity

data class User(
    val name: String,
    val email: String,
    val dateOfBirth: String,
    val password: String,
    val gender: Gender,
    val unit: MeasurementUnit,
    val height: Float,
    val weight: Float,
    val goal: FitnessGoal,
    val tools: List<Tool>,
    val workoutDays: List<WorkoutDay>
)
