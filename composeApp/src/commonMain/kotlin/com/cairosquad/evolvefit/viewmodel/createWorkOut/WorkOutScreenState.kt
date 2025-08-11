package com.cairosquad.evolvefit.viewmodel.createWorkOut

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

data class WorkOutScreenState(
    val image: UiImage = UiImage.ImageUrl(""),
    val name: String = "",
    val goal: String? = null,
    val description: String = "",
    val isNextEnabled: Boolean = false,
    val isImagePickerOpen: Boolean = false,

    val allExercises: List<UiExercise> = emptyList(),
    val filteredExercises: List<UiExercise> = emptyList(),
    val selectedExerciseIds: List<Long> = emptyList(),
    val isAddExercisesEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val exerciseCount : Int =0,

    val currentStep: CreateWorkoutStep = CreateWorkoutStep.DETAILS
) {
    enum class CreateWorkoutStep {
        DETAILS,
        EXERCISES
    }

    data class UiWorkout(
        val id: String = "",
        val name: String = "",
        val level: String = "",
        val description: String = "",
        val imageUrl: String? = null,
        val exercisesId: List<Long> = emptyList()
    )

    data class UiExercise(
        val id: String = "",
        val name: String = "",
        val imageUrl: String = "",
        val duration: UiExerciseDuration
    )

    sealed class UiExerciseDuration {
        data class Time(val seconds: Int) : UiExerciseDuration()
        data class RepsSets(val reps: Int, val sets: Int) : UiExerciseDuration()
    }


    data class ExerciseUiModel(
        val id: String,
        val name: String,
        val imageUrl: String,
        val timeInSeconds: Int? = null,
        val reps: Int? = null,
        val sets: Int? = null
    )
}