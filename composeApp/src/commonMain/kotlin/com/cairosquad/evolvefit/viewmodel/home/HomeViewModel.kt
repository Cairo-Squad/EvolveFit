package com.cairosquad.evolvefit.viewmodel.home

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.home.GetNutritionProgressUseCase
import com.cairosquad.evolvefit.domain.usecase.home.GetPersonalizedWorkoutsUseCase
import com.cairosquad.evolvefit.domain.usecase.home.GetWeeklyProgressUseCase
import com.cairosquad.evolvefit.domain.usecase.home.model.NutritionProgress
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class HomeViewModel(
    private val manageProfileUseCase: ManageProfileUseCase,
    private val getWeeklyProgressUseCase: GetWeeklyProgressUseCase,
    private val getNutritionProgressUseCase: GetNutritionProgressUseCase,
    private val getPersonalizedWorkoutsUseCase: GetPersonalizedWorkoutsUseCase,
) : BaseViewModel<HomeScreenState, HomeScreenEffect>(HomeScreenState()), HomeInteractionListener {

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        startLoading()
        loadUserInfo()
        loadProgress()
        loadNutrition()
        loadPersonalizedWorkouts()
        // TODO: If all data failed to load, stop the loading state and show the error state
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
        stopLoading()
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

        stopLoading()
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

        stopLoading()
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

        stopLoading()
    }

    override fun onWorkoutClick(id: String) {
        sendEffect(HomeScreenEffect.NavigateToWorkout(id))
    }

    override fun onSavedWorkoutClick(id: String) {
        // TODO: add save workout use case and use it here, and move the state update to it's success call back
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
        loadInitialData()
    }

    private fun startLoading() {
        updateState {
            it.copy(isLoading = true)
        }
    }

    private fun stopLoading() {
        updateState {
            it.copy(isLoading = false)
        }
    }

    private fun handleHomeErrors(error: Throwable) {
        // TODO
        println("TEST : $error")
    }

}