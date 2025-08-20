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
                block = { addFavoriteMeal(lastDeletedMeal.id) },
                onError = { error -> },
                onSuccess = {restoreDeletedMeal(lastDeletedMeal, lastMealIndex)
                }
            )
        } else if (lastDeletedWorkout != null && lastWorkoutIndex != null) {
            tryToCall(
                block = { addFavoriteWorkout(lastDeletedWorkout.id) },
                onError = { error -> },
                onSuccess = {restoreDeletedWorkout(lastDeletedWorkout, lastWorkoutIndex)
                }

            )
        }

    }

    override fun deleteMeal(mealId: String) {
        val currentList = screenState.value.mealsList
        val deletedMeal = currentList.find { it.id == mealId }
        val index = currentList.indexOfFirst { it.id == mealId }
        tryToCall(
            block = { deleteFavoriteMeal(mealId) },
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
                }
            }
        )
    }

    override fun deleteWorkout(workoutId: String) {
        val currentList = screenState.value.workoutsList
        val deletedWorkout = currentList.find { it.id == workoutId }
        val index = currentList.indexOfFirst { it.id == workoutId }
        tryToCall(
            block = { deleteFavoriteWorkout(workoutId) },
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
                }
            }
        )
    }

    private fun loadMeals() {
        tryToCall(
            block = ::loadFavoriteMeals,
            onError = { error ->
            },
            onSuccess = { meals ->
                updateState { it.copy(mealsList = meals.map { it.toUiState() }) }
            }
        )
    }

    private fun loadWorkouts() {
        tryToCall(
            block = ::loadFavoriteWorkouts,
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

    private suspend fun loadFavoriteMeals() = getFavoriteMealsUseCase.getFavouriteMeals()

    private suspend fun loadFavoriteWorkouts() = manageWorkoutUseCase.getFavoriteWorkouts()

    private suspend fun deleteFavoriteMeal(mealId: String) =
        getFavoriteMealsUseCase.deleteFavouriteMeal(mealId)

    private suspend fun deleteFavoriteWorkout(workoutId: String) =
        manageWorkoutUseCase.deleteFavouriteWorkout(workoutId)

    private suspend fun addFavoriteMeal(mealId: String) =
        getFavoriteMealsUseCase.addFavouriteMealById(mealId)

    private suspend fun addFavoriteWorkout(workoutId: String) =
        manageWorkoutUseCase.addWorkoutToFavorites(workoutId)

    private fun restoreDeletedWorkout(workout: WorkoutsUiModel, index: Int) {
        val updatedList = screenState.value.workoutsList.toMutableList()
        updatedList.add(index, workout)
        updateState {
            it.copy(
                workoutsList = updatedList,
                lastDeletedWorkout = null,
                lastDeletedWorkoutIndex = null,
                isSnackBarVisible = false
            )
        }
    }
    private fun restoreDeletedMeal(meal: MealsUiModel, index: Int) {
        val updatedList = screenState.value.mealsList.toMutableList()
        updatedList.add(index, meal)
        updateState {
            it.copy(
                mealsList = updatedList,
                lastDeletedMeal = null,
                lastDeletedMealIndex = null,
                isSnackBarVisible = false
            )
        }
    }
}