package com.cairosquad.evolvefit.viewmodel.home

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class HomeViewModel(

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
        // TODO: use the use case, this should be the only use case where the success call back should not stop the loading indicator
    }

    private fun loadProgress() {
        // TODO: use the use case, stop the loading state on success
    }

    private fun loadNutrition() {
        // TODO: use the use case, stop the loading state on success
    }

    private fun loadPersonalizedWorkouts() {
        // TODO: use the use case, stop the loading state on success
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

}