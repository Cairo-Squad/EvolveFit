package com.cairosquad.evolvefit.viewmodel.workoutDetails

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

data class WorkoutDetailsScreenState(
    val workoutImage: UiImage = UiImage.ImageUrl(""),
    val workoutTitle: String="",
    val workoutDescription: String="",
    val detailsCardsInfo: List<DetailsCardInfo> = emptyList(),
    val exercises: List<Exercise> = emptyList(),
    val selectedExercise: ExerciseDetails? = null

) {
    sealed class ExerciseType {
        data class Duration(val seconds: Int) : ExerciseType()
        data class Reps(val count: Int) : ExerciseType()
    }

    data class Exercise(
        val name: String,
        val exerciseImage: UiImage,
        val type: ExerciseType
    )

    data class ExerciseDetails(
        val name: String,
        val instructions: List<String>,
        val image: List<UiImage>,
        val duration: Int?,
        val reps: Int?,
        val equipments: List<String>,
        val focusAreas: List<String>
    )

    data class DetailsCardInfo(
        val value: String,
        val label: String
    )
}