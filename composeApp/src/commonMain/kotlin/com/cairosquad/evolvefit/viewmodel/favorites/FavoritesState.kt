package com.cairosquad.evolvefit.viewmodel.favorites

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.entity.nutrition.MealType
import com.cairosquad.evolvefit.entity.nutrition.SuggestedMeal

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

fun Workout.toUiState(): WorkoutsUiModel {
    return WorkoutsUiModel(
        name = name,
        estimatedTimeInSeconds = estimatedTimeInSeconds,
        focusArea = "", //TODO()
        imageUrl = imageUrl

    )
}

fun WorkoutSuggested.toUiState(): WorkoutsUiModel {
    return WorkoutsUiModel(
        name = name,
        estimatedTimeInSeconds = durationSeconds,
        focusArea = "", //TODO()
        imageUrl = imageUrl

    )
}
