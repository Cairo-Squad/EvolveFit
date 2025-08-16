package com.cairosquad.evolvefit.viewmodel.favorites

import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class FavoritesViewModel(
    private val getFavoriteMealsUseCase: ManageNutritionUseCase,
    private val manageWorkoutUseCase: ManageWorkoutUseCase
) : BaseViewModel<FavoritesState, FavoritesEffect>(FavoritesState()),
    FavoritesInteractionListener {

    init {
        loadMeals()
        loadWorkouts()
    }

    override fun onMealTabSelected() {
        TODO("Not yet implemented")
    }

    override fun onWorkoutTabSelected() {
        TODO("Not yet implemented")
    }

    override fun onBackClicked() {
        sendEffect(FavoritesEffect.NavigateBack)
    }

    private fun loadMeals() {

        tryToCall(
            block = { getFavoriteMealsUseCase.getFavouriteMeals() },
            onError = {},
            onSuccess = { meals ->
                updateState { it.copy(mealsList = meals.map { it.toUiState() }) }
            }
        )
    }

    private fun loadWorkouts() {
        tryToCall(
            block = { manageWorkoutUseCase.getFavoriteWorkouts() },
            onError = {},
            onSuccess = { workouts ->
                updateState { it.copy(workoutsList = workouts.map { it.toUiState() }) }
            }
        )
    }
}