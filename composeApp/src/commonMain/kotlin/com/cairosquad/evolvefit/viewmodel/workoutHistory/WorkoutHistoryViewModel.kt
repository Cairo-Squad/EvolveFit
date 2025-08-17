package com.cairosquad.evolvefit.viewmodel.workoutHistory

import com.cairosquad.evolvefit.domain.entity.WorkoutHistory
import com.cairosquad.evolvefit.domain.usecase.report.ReportUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class WorkoutHistoryViewModel(
    private val reportUseCase: ReportUseCase,
) : BaseViewModel<WorkoutHistoryScreenState, WorkoutHistoryEffect>(WorkoutHistoryScreenState()),
    WorkoutHistoryInteractionListener {

    init {
        loadWorkoutHistory()
    }

    fun loadWorkoutHistory() {
        tryToCall(
            block = reportUseCase::getWorkoutHistory,
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
}