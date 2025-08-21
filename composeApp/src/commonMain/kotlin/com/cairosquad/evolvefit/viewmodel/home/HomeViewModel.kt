package com.cairosquad.evolvefit.viewmodel.home

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.home.GetNutritionProgressUseCase
import com.cairosquad.evolvefit.domain.usecase.home.GetPersonalizedWorkoutsUseCase
import com.cairosquad.evolvefit.domain.usecase.home.model.NutritionProgress
import com.cairosquad.evolvefit.domain.usecase.profile.ManageProfileUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.utils.toErrorMessageRes
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPersonalizedWorkoutsUseCase: GetPersonalizedWorkoutsUseCase,
    private val getNutritionProgressUseCase: GetNutritionProgressUseCase,
    private val manageProfileUseCase: ManageProfileUseCase,
    private val manageWorkoutUseCase: ManageWorkoutUseCase
) : BaseViewModel<HomeScreenState, HomeScreenEffect>(HomeScreenState()), HomeInteractionListener {

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        tryToCall(
            block = { loadAllData() },
            onStart = { startLoading() },
            onSuccess = { stopLoading(HomeScreenState.ScreenStatus.SUCCESS) },
            onError = { throwable ->
                stopLoading(HomeScreenState.ScreenStatus.FAIL)
                updateState { it.copy(screenErrorMessage = throwable.toErrorMessageRes()) }
            }
        )
    }

    private suspend fun loadAllData() {
        coroutineScope {
            val userDeferred = async { loadUserInfo() }
            val progressDeferred = async { loadProgress() }
            val nutritionDeferred = async { loadNutrition() }
            val workoutsDeferred = async { loadPersonalizedWorkouts() }
            awaitAll(userDeferred, progressDeferred, nutritionDeferred, workoutsDeferred)
        }
    }

    private suspend fun loadUserInfo() {
        val userInfo = manageProfileUseCase.getProfile()
        handleLoadUserInfoSuccess(userInfo)
    }

    private fun handleLoadUserInfoSuccess(profile: Profile) {
        updateState {
            it.copy(
                user = profile.toHomeUserUiState()
            )
        }
    }

    private fun loadProgress() {
        // DUMMY
        viewModelScope.launch {
            updateState {
                it.copy(
                    weeklyProgress = DummyDataSource.weeklyProgress,
                )
            }
            // stopLoading()
        }
        // TODO: use the use case, stop the loading state on success
    }

    private suspend fun loadNutrition() {
        loadWaterNutrition()
        loadCaloriesNutrition()
    }

    private suspend fun loadWaterNutrition() {
        val waterConsumed = getNutritionProgressUseCase.getWaterNutrition()
        handleLoadWaterNutritionSuccess(waterConsumed)
    }

    private fun handleLoadWaterNutritionSuccess(nutritionProgress: NutritionProgress<Float>) {
        updateState {
            it.copy(
                waterCount = nutritionProgress.currentProgress,
                waterGoal = nutritionProgress.goal
            )
        }
    }

    private suspend fun loadCaloriesNutrition() {
        val caloriesConsumed = getNutritionProgressUseCase.getCaloriesNutrition()
        handleLoadCaloriesNutritionSuccess(caloriesConsumed)
    }

    private fun handleLoadCaloriesNutritionSuccess(nutritionProgress: NutritionProgress<Int>) {
        updateState {
            it.copy(
                caloriesCount = nutritionProgress.currentProgress.toUInt(),
                caloriesGoal = nutritionProgress.goal.toUInt()
            )
        }
    }

    private suspend fun loadPersonalizedWorkouts() {
        val workoutSuggested = getPersonalizedWorkoutsUseCase.getWorkouts()
        handleLoadPersonalizedWorkoutsSuccess(workoutSuggested)
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

    override fun onWorkoutClick(id: String) {
        sendEffect(HomeScreenEffect.NavigateToWorkout(id))
    }

    override fun onSavedWorkoutClick(id: String) {
        tryToCall(
            block = {  manageWorkoutUseCase.addWorkoutToFavorites(id)},
            onSuccess = {onSaveWorkoutSuccess(id)},
            onError = {throwable->
                updateState { it.copy(screenErrorMessage = throwable.toErrorMessageRes()) }
            },
        )


    }
    private  fun onSaveWorkoutSuccess(id: String){
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
        loadHomeData()
    }
    override fun onRefresh() {
        updateState { it.copy(isRefreshing = true) }
        loadHomeData()
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

}