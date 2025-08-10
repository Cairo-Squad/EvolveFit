package com.cairosquad.evolvefit.entity

enum class FocusArea {
    Quadriceps,
    Abs,
    Calves,
    LowerBack,
    Core,
    Shoulders
}

enum class MeasurementType {
    DURATION, REPS
}

data class Exercise(
    val id: Long = 0L,
    val name: String,
    val imageUrl: String?,
    val equipmentIds: List<Long>,
    val measurementType: MeasurementType,
    val measurementValue: Int,
    val focusAreas: Set<FocusArea>,
    val description: String
)
