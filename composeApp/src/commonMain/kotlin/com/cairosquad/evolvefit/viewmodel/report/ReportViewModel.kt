package com.cairosquad.evolvefit.viewmodel.report

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.entity.Report
import com.cairosquad.evolvefit.domain.entity.WorkoutHistory
import com.cairosquad.evolvefit.domain.usecase.report.ManageReportsUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.last_week
import evolvefit.composeapp.generated.resources.this_week
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

class ReportViewModel(
    private val manageReportsUseCase: ManageReportsUseCase,
) : BaseViewModel<ReportScreenState, ReportEffect>(ReportScreenState()),
    ReportInteractionListener {

    init {
        loadData()
    }

    private fun loadData(){
        loadWorkoutReport()
        loadWorkoutHistory()
        loadWeeks()
    }

    fun loadWorkoutReport(weekRange: Pair<String, String> = getCurrentWeekRange()) {
        tryToCall(
            block = {
                manageReportsUseCase.getReport(
                    weekRange.first,
                    weekRange.second
                )
            },
            onSuccess = ::onLoadWorkoutSuccess,
            onError = ::onLoadWorkoutError
        )
    }

    fun loadWorkoutHistory() {
        tryToCall(
            block = manageReportsUseCase::getWorkoutHistory,
            onSuccess = ::onLoadWorkoutHistorySuccess,
            onError = ::onLoadWorkoutHistoryError
        )
    }

    private fun loadWeeks() {
        updateState {
            it.copy(
                weeks = listOf(THIS_WEEK, LAST_WEEK),
                selectedWeek = ReportScreenState.WeekItem(THIS_WEEK_KEY, Res.string.this_week)
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
        when (item.key) {
            THIS_WEEK_KEY -> loadWorkoutReport()
            LAST_WEEK_KEY -> loadWorkoutReport(getLastWeekRange())
        }
        updateState { state ->
            state.copy(
                isDropDownMenuOpen = false,
                selectedWeek = item
            )
        }
    }

    override fun onRefresh() {
        updateState { it.copy(isRefreshing = true) }
        loadWorkoutReport()
        viewModelScope.launch {
            delay(500)
            updateState { it.copy(isRefreshing = false, selectedWeek = THIS_WEEK) }
        }
    }

    fun getCurrentWeekRange(): Pair<String, String> {
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val today = now.date

        val daysFromSaturday = (today.dayOfWeek.isoDayNumber + 1) % 7
        val startOfWeekDate = today.minus(daysFromSaturday.toLong(), DateTimeUnit.DAY)

        val endOfWeekDate = startOfWeekDate.plus(7, DateTimeUnit.DAY)

        val formatter: (LocalDate) -> String = { date ->
            "$date$START_OF_THE_DAY"
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
            "$date$START_OF_THE_DAY"
        }

        return Pair(formatter(lastWeekSaturday), formatter(lastWeekFriday))
    }

    private companion object {
        const val THIS_WEEK_KEY = "this_week"
        const val LAST_WEEK_KEY = "last_week"
        const val START_OF_THE_DAY = "T00:00:00"
        val THIS_WEEK = ReportScreenState.WeekItem(THIS_WEEK_KEY, Res.string.this_week)
        val LAST_WEEK = ReportScreenState.WeekItem(LAST_WEEK_KEY, Res.string.last_week)
    }
}