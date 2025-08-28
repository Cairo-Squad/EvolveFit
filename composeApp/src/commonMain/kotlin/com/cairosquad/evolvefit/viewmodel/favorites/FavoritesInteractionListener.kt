package com.cairosquad.evolvefit.viewmodel.favorites

interface FavoritesInteractionListener {
  fun onMealTabSelected()
  fun onWorkoutTabSelected()
  fun onBackClicked()
  fun onUndoClicked()
  fun deleteMeal(mealId: String)
  fun deleteWorkout(workoutId: String)
  fun onWorkoutClicked(workoutId: String)
  fun onMealClicked(mealId: String)
}