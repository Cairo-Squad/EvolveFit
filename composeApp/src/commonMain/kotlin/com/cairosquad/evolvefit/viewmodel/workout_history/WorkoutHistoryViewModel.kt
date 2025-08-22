package com.cairosquad.evolvefit.viewmodel.workout_history

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.entity.WorkoutHistory
import com.cairosquad.evolvefit.domain.usecase.report.ManageReportsUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WorkoutHistoryViewModel(
    private val manageReportsUseCase: ManageReportsUseCase,
) : BaseViewModel<WorkoutHistoryScreenState, WorkoutHistoryEffect>(WorkoutHistoryScreenState()),
    WorkoutHistoryInteractionListener {

    init {
        loadWorkoutHistory()
    }

    fun loadWorkoutHistory() {
        tryToCall(
            block = manageReportsUseCase::getWorkoutHistory,
            onSuccess = ::onLoadWorkoutHistorySuccess,
            onError = ::onLoadWorkoutHistoryError
        )
    }

    private fun onLoadWorkoutHistorySuccess(workoutHistory: List<WorkoutHistory>) {
        updateState {
            it.copy(
                workoutHistory = workoutHistory.map { it.toUiState() },
                screenStatus = WorkoutHistoryScreenState.ScreenStatus.SUCCESS
            )
        }
    }

    private fun onLoadWorkoutHistoryError(throwable: Throwable) {
        updateState {
            it.copy(
                screenStatus = WorkoutHistoryScreenState.ScreenStatus.ERROR
            )
        }
    }

    override fun onBackClicked() {
        sendEffect(WorkoutHistoryEffect.NavigateBack)
    }

    override fun onRefresh() {
        updateState {
            it.copy(
                isRefreshing = true,
                screenStatus = WorkoutHistoryScreenState.ScreenStatus.LOADING
            )
        }
        loadWorkoutHistory()
        viewModelScope.launch {
            delay(500)
            updateState { it.copy(isRefreshing = false) }
        }
    }
}