package com.cairosquad.evolvefit.viewmodel.favorites

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoriteMealsUseCase: ManageNutritionUseCase,
    private val manageWorkoutUseCase: ManageWorkoutUseCase
) : BaseViewModel<FavoritesState, FavoritesEffect>(FavoritesState()),
    FavoritesInteractionListener {

    init {
        loadData()
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
        if (hasDeletedMeal()) {
            handleMealUndo()
        } else if (hasDeletedWorkout()) {
            handleWorkoutUndo()
        }
    }

    override fun deleteMeal(mealId: String) {
        val currentList = screenState.value.mealsList
        val deletedMeal = currentList.find { it.id == mealId }
        val index = currentList.indexOfFirst { it.id == mealId }

        tryToCall(
            block = { deleteFavoriteMeal(mealId) },
            onError = {},
            onSuccess = {
                handleMealDeletionSuccess(mealId, deletedMeal, index)
                loadMeals()
            }
        )
    }

    override fun deleteWorkout(workoutId: String) {
        val currentList = screenState.value.workoutsList
        val deletedWorkout = currentList.find { it.id == workoutId }
        val index = currentList.indexOfFirst { it.id == workoutId }

        tryToCall(
            block = { deleteFavoriteWorkout(workoutId) },
            onError = {},
            onSuccess = {
                handleWorkoutDeletionSuccess(workoutId, deletedWorkout, index)
                loadWorkouts()
            }
        )
    }

    private fun loadData() {
        loadMeals()
        loadWorkouts()
    }

    private fun handleMealDeletionSuccess(mealId: String, deletedMeal: MealsUiModel?, index: Int) {
        showSnackBar()

        updateState {
            it.copy(lastDeletedMeal = deletedMeal, lastDeletedMealIndex = index)
        }
        updateState { current ->
            current.copy(mealsList = current.mealsList.filterNot { it.id == mealId })
        }
    }

    private fun handleWorkoutDeletionSuccess(
        workoutId: String,
        deletedWorkout: WorkoutsUiModel?,
        index: Int
    ) {
        showSnackBar()

        updateState {
            it.copy(
                lastDeletedWorkout = deletedWorkout,
                lastDeletedWorkoutIndex = index
            )
        }

        updateState { current ->
            current.copy(workoutsList = current.workoutsList.filterNot { it.id == workoutId })
        }
    }


    private fun loadMeals() {
        tryToCall(
            onStart = { loadingMealsState(true) },
            block = ::loadFavoriteMeals,
            onError = { loadingMealsState(false) },
            onSuccess = ::onloadMealsSuccess
        )
    }

    private fun onloadMealsSuccess(meals: List<SuggestedMeal>) {
        loadingMealsState(false)
        updateState { it.copy(mealsList = meals.map { it.toUiState() }) }
    }

    private fun loadWorkouts() {
        tryToCall(
            onStart = { loadingWorkoutsState(true) },
            block = ::loadFavoriteWorkouts,
            onError = { loadingWorkoutsState(false) },
            onSuccess = ::onloadWorkoutsSuccess
        )
    }

    private fun onloadWorkoutsSuccess(workouts: List<WorkoutSuggested>) {
        loadingWorkoutsState(false)
        updateState { it.copy(workoutsList = workouts.map { it.toUiState() }) }
    }

    private fun loadingMealsState(isLoading: Boolean) =
        updateState { it.copy(isMealsLoading = isLoading) }

    private fun loadingWorkoutsState(isLoading: Boolean) =
        updateState { it.copy(isWorkoutsLoading = isLoading) }

    private var snackBarJob: Job? = null
    private fun showSnackBar() {
        snackBarJob?.cancel()
        snackBarJob = viewModelScope.launch(Dispatchers.Main) {
            updateState { it.copy(isSnackBarVisible = true) }
            delay(3000)
            updateState {
                it.copy(
                    isSnackBarVisible = false,
                    lastDeletedMeal = null,
                    lastDeletedMealIndex = null,
                    lastDeletedWorkout = null,
                    lastDeletedWorkoutIndex = null
                )
            }
        }
    }

    private fun hasDeletedMeal() =
        screenState.value.lastDeletedMeal != null && screenState.value.lastDeletedMealIndex != null

    private fun hasDeletedWorkout() =
        screenState.value.lastDeletedWorkout != null && screenState.value.lastDeletedWorkoutIndex != null

    private fun handleMealUndo() {
        val meal = screenState.value.lastDeletedMeal!!
        val index = screenState.value.lastDeletedMealIndex!!
        tryToCall(
            block = { addFavoriteMeal(meal.id) },
            onError = { },
            onSuccess = { restoreDeletedMeal(meal, index) }
        )
    }

    private fun handleWorkoutUndo() {
        val workout = screenState.value.lastDeletedWorkout!!
        val index = screenState.value.lastDeletedWorkoutIndex!!
        tryToCall(
            block = { addFavoriteWorkout(workout.id) },
            onError = { },
            onSuccess = { restoreDeletedWorkout(workout, index) }
        )
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
        val safeIndex = minOf(index, updatedList.size)
        updatedList.add(safeIndex, workout)

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
        val safeIndex = minOf(index, updatedList.size)
        updatedList.add(safeIndex, meal)
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