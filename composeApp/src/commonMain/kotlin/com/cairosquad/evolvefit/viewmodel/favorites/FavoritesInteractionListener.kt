package com.cairosquad.evolvefit.viewmodel.favorites

interface FavoritesInteractionListener {
  fun onMealTabSelected()
  fun onWorkoutTabSelected()
  fun onBackClicked()
  fun onUndoClicked()
}