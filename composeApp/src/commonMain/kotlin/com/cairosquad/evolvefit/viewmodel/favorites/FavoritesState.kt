package com.cairosquad.evolvefit.viewmodel.favorites

import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.model.MealType

data class FavoritesState(
    val isLoading: Boolean = false,
    val isMealsLoading: Boolean = false,
    val isWorkoutsLoading: Boolean = false,
    val workoutsList: List<WorkoutsUiModel> = emptyList(),
    val mealsList: List<MealsUiModel> = emptyList(),
    val isWorkoutTabSelected: Boolean = true,
    val isMealTabSelected: Boolean = false,
    val isSnackBarVisible: Boolean = false,
    val lastDeletedMeal: MealsUiModel? = null,
    val lastDeletedWorkout: WorkoutsUiModel? = null,
    val lastDeletedMealIndex: Int? = null,
    val lastDeletedWorkoutIndex: Int? = null,
    )

data class WorkoutsUiModel(
    val id: String,
    val name: String,
    val estimatedTimeInSeconds: Int?,
    val focusArea: String,
    val imageUrl: String
)

data class MealsUiModel(
    val id: String,
    val name: String,
    val type: MealType,
    val calories: Int,
    val imageUrl: String
)

fun SuggestedMeal.toUiState(): MealsUiModel {
    return MealsUiModel(
        id = id,
        name = name,
        type = type,
        calories = calories,
        imageUrl = imageUrl
    )
}

fun WorkoutSuggested.toUiState(): WorkoutsUiModel {
    return WorkoutsUiModel(
        id = id,
        name = name,
        estimatedTimeInSeconds = durationSeconds,
        focusArea = focusArea.joinToString(separator = " , ") { it.name },
        imageUrl = imageUrl

    )
}
