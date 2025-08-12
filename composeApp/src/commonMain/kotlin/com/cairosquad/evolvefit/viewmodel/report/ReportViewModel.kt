package com.cairosquad.evolvefit.viewmodel.report

import com.cairosquad.evolvefit.domain.entity.Report
import com.cairosquad.evolvefit.domain.usecase.report.ReportUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class ReportViewModel(
    private val reportUseCase: ReportUseCase
) : BaseViewModel<ReportScreenState, ReportEffect>(ReportScreenState()),
    ReportInteractionListener {

    init {
        loadWorkoutReport()
    }

    fun loadWorkoutReport() {
        tryToCall(
            block = reportUseCase::getReport,
            onSuccess = ::onLoadWorkoutSuccess,
            onError = ::onLoadWorkoutError
        )
    }

    private fun onLoadWorkoutSuccess(report: Report) {
        updateState { it.copy(report = report.toUiState()) }
    }

    private fun onLoadWorkoutError(throwable: Throwable) {

    }

    override fun onViewAllHistoryWorkoutsClicked() {
        sendEffect(ReportEffect.navigateToAllHistoryWorkouts)
    }

    override fun onShareClicked() {
        sendEffect(ReportEffect.onShareClicked)
    }

    override fun onDropDownMenuClicked() {
        updateState { it.copy(isDropDownMenuOpen = true) }
    }

    override fun onDropDownMenuDismiss() {
        updateState { it.copy(isDropDownMenuOpen = false) }
    }

    override fun onDropDownMenuItemClicked(item: String) {
        updateState { it.copy(isDropDownMenuOpen = false) }
    }
}