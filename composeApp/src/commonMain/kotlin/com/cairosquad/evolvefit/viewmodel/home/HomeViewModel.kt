package com.cairosquad.evolvefit.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.home.GetNutritionProgressUseCase
import com.cairosquad.evolvefit.domain.usecase.home.GetPersonalizedWorkoutsUseCase
import com.cairosquad.evolvefit.domain.usecase.home.GetWeeklyProgressUseCase
import com.cairosquad.evolvefit.domain.usecase.home.model.NutritionProgress
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.utils.toErrorMessageRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val manageProfileUseCase: ManageProfileUseCase,
    private val getWeeklyProgressUseCase: GetWeeklyProgressUseCase,
    private val getNutritionProgressUseCase: GetNutritionProgressUseCase,
    private val manageWorkoutUseCase: ManageWorkoutUseCase,
    private val getPersonalizedWorkoutsUseCase: GetPersonalizedWorkoutsUseCase,
) : BaseViewModel<HomeScreenState, HomeScreenEffect>(HomeScreenState()), HomeInteractionListener {

    init {
        loadAllData()
    }

    private fun loadAllData() {
        startLoading()
        loadUserInfo()
        loadProgress()
        loadNutrition()
        loadPersonalizedWorkouts()
    }

    private fun loadUserInfo() {
        tryToCall(
            block = { manageProfileUseCase.getProfile() },
            onSuccess = ::handleLoadUserInfoSuccess,
            onError = ::handleHomeErrors
        )
    }

    private fun handleLoadUserInfoSuccess(profile: Profile) {
        updateState {
            it.copy(
                user = profile.toHomeUserUiState(),
                weeklyProgress = profile.toWeeklyProgressUiState(it.weeklyProgress)
            )
        }

        stopLoading(HomeScreenState.ScreenStatus.SUCCESS)
    }

    private fun loadProgress() {
        tryToCall(
            block = { getWeeklyProgressUseCase.getWeeklyProgress() },
            onSuccess = ::handleLoadProgressSuccess,
            onError = ::handleHomeErrors
        )
    }

    private fun handleLoadProgressSuccess(progress: WeeklyProgress) {
        updateState {
            it.copy(
                weeklyProgress = progress.toWeeklyProgressUiState(it.weeklyProgress)
            )
        }
        stopLoading(HomeScreenState.ScreenStatus.SUCCESS)
    }

    private fun loadNutrition() {
        loadWaterNutrition()
        loadCaloriesNutrition()
    }

    private fun loadWaterNutrition() {
        tryToCall(
            block = { getNutritionProgressUseCase.getWaterNutrition() },
            onSuccess = ::handleLoadWaterNutritionSuccess,
            onError = ::handleHomeErrors
        )
    }

    private fun handleLoadWaterNutritionSuccess(nutritionProgress: NutritionProgress<Float>) {
        updateState {
            it.copy(
                waterCount = nutritionProgress.currentProgress,
                waterGoal = nutritionProgress.goal
            )
        }

        stopLoading(HomeScreenState.ScreenStatus.SUCCESS)
    }

    private fun loadCaloriesNutrition() {
        tryToCall(
            block = { getNutritionProgressUseCase.getCaloriesNutrition() },
            onSuccess = ::handleLoadCaloriesNutritionSuccess,
            onError = ::handleHomeErrors
        )
    }

    private fun handleLoadCaloriesNutritionSuccess(nutritionProgress: NutritionProgress<Int>) {
        updateState {
            it.copy(
                caloriesCount = nutritionProgress.currentProgress.toUInt(),
                caloriesGoal = nutritionProgress.goal.toUInt()
            )
        }

        stopLoading(HomeScreenState.ScreenStatus.SUCCESS)
    }

    private fun loadPersonalizedWorkouts() {
        tryToCall(
            block = { getPersonalizedWorkoutsUseCase.getWorkouts() },
            onSuccess = ::handleLoadPersonalizedWorkoutsSuccess,
            onError = ::handleHomeErrors,
            onEnd = ::loadFavoriteWorkouts
        )
    }

    private fun handleLoadPersonalizedWorkoutsSuccess(workouts: List<WorkoutSuggested>) {
        updateState {
            it.copy(
                personalizedWorkouts = workouts.map { workout ->
                    workout.toHomeWorkoutUiState(
                        isSaved = false
                    )
                }
            )
        }
    }

    private fun loadFavoriteWorkouts() {
        tryToCall(
            block = { manageWorkoutUseCase.getFavoriteWorkouts() },
            onSuccess = ::handleLoadFavoriteWorkoutsSuccess,
            onError = ::handleHomeErrors
        )
    }

    private fun handleLoadFavoriteWorkoutsSuccess(workouts: List<WorkoutSuggested>) {
        updateWorkouts(workouts.map { it.toHomeWorkoutUiState(isSaved = true) })
    }

    private fun updateWorkouts(workouts: List<HomeScreenState.HomeWorkoutUiState>) {
        updateState {
            it.copy(
                personalizedWorkouts = it.personalizedWorkouts.map { workout ->
                    getWorkoutWithSavedStatus(
                        workout = workout,
                        workouts = workouts
                    )
                }
            )
        }

        stopLoading(HomeScreenState.ScreenStatus.SUCCESS)
    }

    private fun getWorkoutWithSavedStatus(
        workout: HomeScreenState.HomeWorkoutUiState,
        workouts: List<HomeScreenState.HomeWorkoutUiState>
    ): HomeScreenState.HomeWorkoutUiState {
        return if (workouts.map { it.id }.contains(workout.id)) {
            workout.copy(isSaved = true)
        } else {
            workout
        }
    }

    override fun onWorkoutClick(id: String) {
        sendEffect(HomeScreenEffect.NavigateToWorkout(id))
    }

    override fun onSavedWorkoutClick(id: String, isSaved: Boolean) {
        tryToCall(
            block = { toggleWorkoutSavedStatus(id, isSaved) },
            onSuccess = { handleSaveWorkoutSuccess(id) },
            onError = ::handleHomeErrors,
        )
    }

    private suspend fun toggleWorkoutSavedStatus(id: String, isSaved: Boolean) {
        if (isSaved) {
            manageWorkoutUseCase.deleteFavouriteWorkout(id)
        } else {
            manageWorkoutUseCase.addWorkoutToFavorites(id)
        }
    }

    private fun handleSaveWorkoutSuccess(id: String) {
        updateState {
            it.copy(
                personalizedWorkouts = it.personalizedWorkouts.map { workout ->
                    if (workout.id == id) {
                        workout.copy(isSaved = !workout.isSaved)
                    } else {
                        workout
                    }
                }
            )
        }
    }

    override fun onRetryClick() {
        loadAllData()
    }

    override fun onRefresh() {
        updateState { it.copy(isRefreshing = true) }
        loadAllData()
        viewModelScope.launch {
            delay(500L)
            updateState { it.copy(isRefreshing = false) }
        }
    }

    private fun startLoading() {
        updateState {
            it.copy(
                screenStatus = HomeScreenState.ScreenStatus.LOADING
            )
        }

        observeRequest()
    }

    private fun observeRequest() {
        viewModelScope.launch(Dispatchers.Unconfined) {
            delay(REQUEST_TIME_LIMIT_IN_MILLISECONDS)
            if (screenState.value.screenStatus == HomeScreenState.ScreenStatus.LOADING) {
                stopLoading(HomeScreenState.ScreenStatus.FAIL)
            }
        }
    }

    private fun stopLoading(screenStatus: HomeScreenState.ScreenStatus) {
        updateState {
            it.copy(
                screenStatus = screenStatus
            )
        }
    }

    private fun handleHomeErrors(error: Throwable) {
        updateState { it.copy(screenErrorMessage = error.toErrorMessageRes()) }
    }

    companion object {
        private const val REQUEST_TIME_LIMIT_IN_MILLISECONDS = 7000L
    }
}