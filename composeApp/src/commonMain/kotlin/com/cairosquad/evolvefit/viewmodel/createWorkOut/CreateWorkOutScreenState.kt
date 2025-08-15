package com.cairosquad.evolvefit.viewmodel.createWorkOut

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState.ExerciseUiModel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

data class CreateWorkOutScreenState(
    val image: UiImage = UiImage.ImageUrl(""),
    val name: String = "",
    val goal: WorkoutLevel= WorkoutLevel.BEGINNER,
    val description: String = "",
    val isNextEnabled: Boolean = false,
    val isImagePickerOpen: Boolean = false,

    val allExercises: List<Exercise> = emptyList(),
    val filteredExercises: List<Exercise> = emptyList(),
    val selectedExercises: List<Exercise> = emptyList(),
    val isAddExercisesEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val exerciseCount : Int =0,

    val currentStep: CreateWorkoutStep = CreateWorkoutStep.DETAILS,
    val status: ScreenStatus = ScreenStatus.LOADING,
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
        val exercises: List<ExerciseUiModel> = emptyList(),
    )

    enum class WorkoutLevel {
        BEGINNER,
        INTERMEDIATE,
        ADVANCED
    }

    enum class ScreenStatus {
        LOADING,
        SUCCESS,
        ERROR,
        EMPTY
    }
}