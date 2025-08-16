package com.cairosquad.evolvefit.viewmodel.report

import com.cairosquad.evolvefit.domain.entity.Report
import com.cairosquad.evolvefit.domain.entity.WorkoutHistory
import com.cairosquad.evolvefit.domain.usecase.report.ReportUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.last_week
import evolvefit.composeapp.generated.resources.this_week
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class ReportViewModel(
    private val reportUseCase: ReportUseCase,
) : BaseViewModel<ReportScreenState, ReportEffect>(ReportScreenState()),
    ReportInteractionListener {

    init {
        loadWorkoutReport()
        loadWorkoutHistory()
        loadWeeks()
    }

    fun loadWorkoutReport() {
        tryToCall(
            block = {
                reportUseCase.getReport(
                    getCurrentWeekRange().first,
                    getCurrentWeekRange().second
                )
            },
            onSuccess = ::onLoadWorkoutSuccess,
            onError = ::onLoadWorkoutError
        )
    }

    fun loadWorkoutHistory() {
        tryToCall(
            block = reportUseCase::getWorkoutHistory,
            onSuccess = ::onLoadWorkoutHistorySuccess,
            onError = ::onLoadWorkoutHistoryError

        )
    }

    private fun loadWeeks() {
        updateState {
            it.copy(
                weeks = listOf(
                    ReportScreenState.WeekItem("this_week", Res.string.this_week),
                    ReportScreenState.WeekItem("last_week", Res.string.last_week)
                ),
                selectedWeek = ReportScreenState.WeekItem("this_week", Res.string.this_week)
            )
        }
    }

    private fun onLoadWorkoutHistorySuccess(workoutHistory: List<WorkoutHistory>) {
        updateState { it.copy(workoutHistory = workoutHistory.map { it.toUiState() }) }
    }

    private fun onLoadWorkoutHistoryError(throwable: Throwable) {

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

    override fun onDropDownMenuItemClicked(item: ReportScreenState.WeekItem) {
        updateState { state ->
            state.copy(
                isDropDownMenuOpen = false,
                selectedWeek = item
            )
        }
    }

    fun getCurrentWeekRange(): Pair<String, String> {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val today = now.date

        val daysFromSaturday = (today.dayOfWeek.isoDayNumber + 1) % 7
        val startOfWeekDate = today.minus(daysFromSaturday.toLong(), DateTimeUnit.DAY)

        val endOfWeekDate = startOfWeekDate.plus(7, DateTimeUnit.DAY)

        val formatter: (LocalDate) -> String = { date ->
            "${date}T00:00:00"
        }

        return Pair(formatter(startOfWeekDate), formatter(endOfWeekDate))
    }

    fun getLastWeekRange(): Pair<String, String> {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val today = now.date

        val daysFromCurrentSaturday = (today.dayOfWeek.isoDayNumber + 1) % 7
        val currentWeekSaturday = today.minus(daysFromCurrentSaturday.toLong(), DateTimeUnit.DAY)

        val lastWeekSaturday = currentWeekSaturday.minus(7, DateTimeUnit.DAY)

        val lastWeekFriday = lastWeekSaturday.plus(6, DateTimeUnit.DAY)

        val formatter: (LocalDate) -> String = { date ->
            "${date}T00:00:00"
        }

        return Pair(formatter(lastWeekSaturday), formatter(lastWeekFriday))
    }
}