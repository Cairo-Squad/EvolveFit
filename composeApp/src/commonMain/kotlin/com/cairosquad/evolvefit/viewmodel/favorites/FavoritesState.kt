package com.cairosquad.evolvefit.viewmodel.favorites

import com.cairosquad.evolvefit.domain.entity.Workout

data class FavoritesState(
   val isLoading: Boolean = false,
    val workoutsList: List<WorkoutsUiModel> = emptyList(),
    val mealsList: List<MealsUiModel> = emptyList(),
    val isWorkoutTabSelected: Boolean = true,
    val isMealTabSelected: Boolean = false
)

data class WorkoutsUiModel(
    val name: String,
    val estimatedTimeInSeconds: Int,
    val focusArea: String,
    val imageUrl: String
)

data class MealsUiModel(
    val name: String,
    val type: MealType,
    val imageUrl: String,
    val nutrition: Nutrition,
){
    data class Nutrition(
        val caloriesInKcal: Int,
        val fatInGrams: Int,
        val proteinInGrams: Int,
        val carbsInGrams: Int
    )
}


enum class MealType {
    BREAKFAST,
    LUNCH,
    DINNER,
    SNACKS
}

fun Workout.toUiState(): WorkoutsUiModel {
    return WorkoutsUiModel(
        name = name,
        estimatedTimeInSeconds = estimatedTimeInSeconds,
        focusArea = focusAreas.toString(),
        imageUrl = imageUrl

    )
}