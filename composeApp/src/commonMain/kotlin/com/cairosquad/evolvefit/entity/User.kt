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
    val notifications: NotificationSettings,
    val workoutDays: List<WorkoutDay>
)
enum class Gender {
    MALE, FEMALE
}

enum class MeasurementUnit {
    METRIC, IMPERIAL
}

enum class FitnessGoal {
    LOSE_WEIGHT, GAIN_WEIGHT, STAY_IN_SHAPE
}

enum class Tool {
    DUMBBELL, MAT, RESISTANCE_BAND, NONE // add more if needed
}

data class NotificationSettings(
    val workoutReminder: Boolean,
    val waterReminder: Boolean,
    val weightReminder: Boolean,
    val achievementsReminder: Boolean
)

enum class WorkoutDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
