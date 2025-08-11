package com.cairosquad.evolvefit.domain.entity

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
    val equipments: List<Equipment>,
    val workoutDays: List<WorkoutDay>
)
