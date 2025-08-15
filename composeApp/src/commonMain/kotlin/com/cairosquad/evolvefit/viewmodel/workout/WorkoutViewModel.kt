package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class WorkoutViewModel(
    private val workoutUseCase: ManageWorkoutUseCase,

    ) : BaseViewModel<WorkoutScreenState, WorkoutEffect>(
    WorkoutScreenState()
), WorkoutInteractionListener {
    init {
        println("WorkoutViewModel")
        loadAllWorkouts()
    }

    private fun loadAllWorkouts() {
        tryToCall(
            block = workoutUseCase::getSuggestedWorkouts,
            onSuccess = ::onGetSuggestedWorkoutsSuccess,
            onError = ::onGetSuggestedWorkoutError,
        )
    }

    private fun loadWorkoutsByFocusArea(focusAreaUiState: WorkoutScreenState.FocusAreaUiState) {
        tryToCall(
            block = {
                TODO()
//                if (focusAreaUiState == WorkoutScreenState.FocusAreaUiState.FULL_BODY) {
//                    workoutUseCase.getSuggestedWorkouts()
//                } else {
//                    workoutUseCase.getWorkoutsByFocusArea(focusAreaUiState.toDomain())
//                }
            },
            onSuccess = ::onLoadWorkoutByFocusAreaSuccess,
            onError = ::onLoadWorkoutByFocusAreaError
        )
    }

    override fun onSelectFocusArea(focusArea: WorkoutScreenState.FocusAreaUiState) {
        updateState { it.copy(selectedFocusArea = focusArea) }

        if (focusArea == WorkoutScreenState.FocusAreaUiState.FULL_BODY) {
            loadAllWorkouts()
        } else {
            loadWorkoutsByFocusArea(focusArea)
        }
    }

    override fun onClickWorkout(id: String) {
        sendEffect(WorkoutEffect.NavigateToWorkoutDetails(id))
    }

    override fun onClickAddWorkout() {
        sendEffect(WorkoutEffect.NavigateToCreateWorkout)
    }

    override fun onClickCommunity() {
        sendEffect(WorkoutEffect.NavigateToCommunityWorkout)
    }

    private fun onGetSuggestedWorkoutsSuccess(workouts: List<WorkoutSuggested>) {
        println("DEBUG: Received ${workouts.size} workouts from API")
        workouts.forEach {
            println("DEBUG: Workout name = ${it.name}")
        }
        updateState { st -> st.copy(allWorkouts = workouts.map { it.toUiState() }) }
    }

    private fun onGetSuggestedWorkoutError(t: Throwable) {
        println("Error fetching suggested workouts")
        println("Error:$t")
        // TODO: show snackbar/effect
    }

    private fun onLoadWorkoutByFocusAreaSuccess(workouts: List<Workout>) {
        //updateState { st -> st.copy(allWorkouts = workouts.map { it.toUiState() }) }
    }

    private fun onLoadWorkoutByFocusAreaError(t: Throwable) {
        // TODO:  snackbar/effect
    }
}