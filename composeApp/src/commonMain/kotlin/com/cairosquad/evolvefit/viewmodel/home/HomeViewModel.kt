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
            onError = ::handleHomeErrors
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

        stopLoading(HomeScreenState.ScreenStatus.SUCCESS)
    }

    override fun onWorkoutClick(id: String) {
        sendEffect(HomeScreenEffect.NavigateToWorkout(id))
    }

    override fun onSavedWorkoutClick(id: String) {
        tryToCall(
            block = { manageWorkoutUseCase.addWorkoutToFavorites(id) },
            onSuccess = { handleSaveWorkoutSuccess(id) },
            onError = ::handleHomeErrors,
        )
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
                isLoading = true,
                screenStatus = HomeScreenState.ScreenStatus.LOADING
            )
        }
    }

    private fun stopLoading(screenStatus: HomeScreenState.ScreenStatus) {
        updateState {
            it.copy(
                isLoading = false,
                screenStatus = screenStatus
            )
        }
    }

    private fun handleHomeErrors(error: Throwable) {
        // TODO
        updateState { it.copy(screenErrorMessage = error.toErrorMessageRes()) }
        println("error: $error")
    }

}