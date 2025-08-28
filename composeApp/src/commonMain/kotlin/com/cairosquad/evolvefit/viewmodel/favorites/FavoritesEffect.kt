package com.cairosquad.evolvefit.viewmodel.favorites

sealed class FavoritesEffect {
    object NavigateBack : FavoritesEffect()
    data class NavigateToWorkoutDetails(val workoutId: String) : FavoritesEffect()
    data class NavigateToMealDetails(val mealId: String) : FavoritesEffect()
}