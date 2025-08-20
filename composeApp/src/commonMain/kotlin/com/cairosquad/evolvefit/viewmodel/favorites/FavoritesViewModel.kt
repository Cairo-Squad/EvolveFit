package com.cairosquad.evolvefit.viewmodel.favorites

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        updateState { it.copy(isMealTabSelected = true, isWorkoutTabSelected = false) }
    }

    override fun onWorkoutTabSelected() {
        updateState { it.copy(isMealTabSelected = false, isWorkoutTabSelected = true) }
    }

    override fun onBackClicked() {
        sendEffect(FavoritesEffect.NavigateBack)
    }

    override fun onUndoClicked() {
        val lastDeletedMeal = screenState.value.lastDeletedMeal
        val lastMealIndex = screenState.value.lastDeletedMealIndex
        val lastDeletedWorkout = screenState.value.lastDeletedWorkout
        val lastWorkoutIndex = screenState.value.lastDeletedWorkoutIndex

        if (lastDeletedMeal != null && lastMealIndex != null) {
            tryToCall(
                block = { getFavoriteMealsUseCase.addFavouriteMealById(lastDeletedMeal.id) },
                onError = { error -> },
                onSuccess = {
                    val updatedList = screenState.value.mealsList.toMutableList()
                    updatedList.add(lastMealIndex, lastDeletedMeal)
                    updateState {
                        it.copy(
                            mealsList = updatedList,
                            lastDeletedMeal = null,
                            lastDeletedMealIndex = null,
                            isSnackBarVisible = false
                        )
                    }
                }
            )
        } else if (lastDeletedWorkout != null && lastWorkoutIndex != null) {
            tryToCall(
                block = { manageWorkoutUseCase.addWorkoutToFavorites(lastDeletedWorkout.id) },
                onError = { error -> },
                onSuccess = {
                    updateState {
                        val updatedList = screenState.value.workoutsList.toMutableList()
                        updatedList.add(lastWorkoutIndex, lastDeletedWorkout)

                        it.copy(
                            workoutsList = updatedList,
                            lastDeletedWorkout = null,
                            lastDeletedWorkoutIndex = null,
                            isSnackBarVisible = false
                        )
                    }
                }

            )
        }

    }

    override fun deleteMeal(mealId: String) {
        val currentList = screenState.value.mealsList
        val deletedMeal = currentList.find { it.id == mealId }
        val index = currentList.indexOfFirst { it.id == mealId }
        tryToCall(
            block = { getFavoriteMealsUseCase.deleteFavouriteMeal(mealId) },
            onError = { error -> },
            onSuccess = {
                showSnackBar()
                updateState {
                    it.copy(
                        lastDeletedMeal = deletedMeal,
                        lastDeletedMealIndex = index
                    )
                }
                updateState { current ->
                    current.copy(mealsList = current.mealsList.filterNot { it.id == mealId })
                }            }
        )
    }

    override fun deleteWorkout(workoutId: String) {
        val currentList = screenState.value.workoutsList
        val deletedWorkout = currentList.find { it.id == workoutId }
        val index = currentList.indexOfFirst { it.id == workoutId }
        tryToCall(
            block = { manageWorkoutUseCase.deleteFavouriteWorkout(workoutId) },
            onError = { error -> },
            onSuccess = {
                updateState {
                    showSnackBar()
                    it.copy(
                        lastDeletedWorkout = deletedWorkout,
                        lastDeletedWorkoutIndex = index
                    )
                }
                updateState { current ->
                    current.copy(workoutsList = current.workoutsList.filterNot { it.id == workoutId })
                }            }
        )
    }

    private fun loadMeals() {
        tryToCall(
            block = { getFavoriteMealsUseCase.getFavouriteMeals() },
            onError = { error ->
            },
            onSuccess = { meals ->
                updateState { it.copy(mealsList = meals.map { it.toUiState() }) }
            }
        )
    }

    private fun loadWorkouts() {
        tryToCall(
            block = { manageWorkoutUseCase.getFavoriteWorkouts() },
            onError = { error ->
            },
            onSuccess = { workouts ->
                updateState { it.copy(workoutsList = workouts.map { it.toUiState() }) }
            }
        )
    }

    private fun showSnackBar() {
        viewModelScope.launch(Dispatchers.Main) {
            updateState {
                it.copy(
                    isSnackBarVisible = true,
                )
            }
            delay(3000)
            updateState {
                it.copy(
                    isSnackBarVisible = false,
                )
            }
        }
    }

}