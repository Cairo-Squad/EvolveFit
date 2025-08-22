package com.cairosquad.evolvefit.viewmodel.create_workout

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.create

data class CreateWorkOutScreenState(
    val image: UiImage = UiImage.ImageResource(Res.drawable.create),
    val name: String = "",
    val level: WorkoutLevel ?=null,
    val description: String = "",
    val isNextEnabled: Boolean = false,
    val isImagePickerOpen: Boolean = false,

    val allExercises: List<ExerciseUiState> = emptyList(),
    val filteredExercises: List<ExerciseUiState> = emptyList(),
    val selectedExercises: List<ExerciseUiState> = emptyList(),
    val isAddExercisesEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val exerciseCount: Int =0,

    val currentStep: CreateWorkoutStep = CreateWorkoutStep.DETAILS,
    val status: ScreenStatus = ScreenStatus.LOADING,
) {
    enum class CreateWorkoutStep {
        DETAILS,
        EXERCISES
    }

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

    data class ExerciseUiState(
        val id: String,
        val name: String,
        val instructions: List<String>,
        val images: List<String>,
        val type: ExerciseType,
        val equipment: EquipmentUiState,
        val focusAreas: List<FocusArea>
    )

    sealed class ExerciseType {
        data class Duration(val seconds: Int) : ExerciseType()
        data class Reps(val count: Int) : ExerciseType()
    }

    enum class FocusArea {
        BACK,
        LEGS,
        SHOULDERS,
        ARMS,
        CORE,
        CHEST
    }

    data class EquipmentUiState(
        val name: String = "",
        val id: Int = 0,
    )
}