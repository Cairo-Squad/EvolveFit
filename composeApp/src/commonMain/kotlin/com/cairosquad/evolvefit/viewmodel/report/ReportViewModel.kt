package com.cairosquad.evolvefit.viewmodel.report

import com.cairosquad.evolvefit.domain.entity.FocusArea
import com.cairosquad.evolvefit.domain.entity.WorkoutDay
import com.cairosquad.evolvefit.domain.usecase.report.ReportUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.report.ReportScreenState.TrainedMuscle

class ReportViewModel(
    private val reportUseCase: ReportUseCase
) : BaseViewModel<ReportScreenState, ReportEffect>(ReportScreenState()),
    ReportInteractionListener {

    init {
        loadWorkoutReport()
    }

    fun loadWorkoutReport() {
        getTimeSpend()
        getCalories()
        getWaterTakenInLiter()
        getTotalWorkouts()
        getWorkoutsPerWeek()
        getTimeSpendPerWeek()
        getFocusedArea()
    }

    private fun getTimeSpend() {
        tryToCall(
            block = reportUseCase::getTimeSpend,
            onSuccess = ::onGetTimeSpendSuccess,
            onError = ::onGetTimeSpendError
        )
    }

    private fun onGetTimeSpendSuccess(timeSpend: Long) {
        updateState { it.copy(timeSpend = formatDuration(timeSpend)) }
    }

    private fun onGetTimeSpendError(throwable: Throwable) {

    }

    private fun getCalories() {
        getExpectedCalories()
        getTakenCalories()
    }

    private fun getTakenCalories() {
        tryToCall(
            block = reportUseCase::getTakenCalories,
            onSuccess = ::onGetTakenCaloriesSuccess,
            onError = ::onGetTakenCaloriesError
        )
    }

    private fun onGetTakenCaloriesSuccess(takenCalories: Int) {
        updateState { it.copy(caloriesTaken = takenCalories) }
    }

    private fun onGetTakenCaloriesError(throwable: Throwable) {

    }

    private fun getExpectedCalories() {
        tryToCall(
            block = reportUseCase::getExpectedCalories,
            onSuccess = ::onGetExpectedCaloriesSuccess,
            onError = ::onGetExpectedCaloriesError

        )
    }

    private fun onGetExpectedCaloriesSuccess(expectedCalories: Int) {
        updateState { it.copy(expectedCalories = expectedCalories) }
    }

    private fun onGetExpectedCaloriesError(throwable: Throwable) {

    }

    private fun getWaterTakenInLiter() {
        tryToCall(
            block = reportUseCase::getWaterTakenInLiter,
            onSuccess = ::onGetWaterTakenInLiterSuccess,
            onError = ::onGetWaterTakenInLiterError
        )
    }

    private fun onGetWaterTakenInLiterSuccess(waterTakenInLiter: Float) {
        updateState { it.copy(waterConsumed = waterTakenInLiter) }
    }

    private fun onGetWaterTakenInLiterError(throwable: Throwable) {

    }

    private fun getWorkoutsPerWeek() {
        tryToCall(
            block = reportUseCase::getWorkoutsPerWeek,
            onSuccess = ::onGetWorkoutsPerWeekSuccess,
            onError = ::onGetWorkoutsPerWeekError
        )
    }

    private fun onGetWorkoutsPerWeekSuccess(workoutsPerWeek: List<Pair<WorkoutDay, Int>>) {
        updateState {
            it.copy(
                workoutPerWeek = ReportScreenState.WorkoutPerDay(
                    day = workoutsPerWeek.map { (day, _) -> day.name.take(3)  },
                    workoutsCount = workoutsPerWeek.map { (_, count) -> count },
                )
            )
        }
    }

    private fun onGetWorkoutsPerWeekError(throwable: Throwable) {

    }

    private fun getTotalWorkouts() {
        tryToCall(
            block = reportUseCase::getTotalWorkouts,
            onSuccess = ::onGetTotalWorkoutsSuccess,
            onError = ::onGetTotalWorkoutsError
        )
    }

    private fun onGetTotalWorkoutsSuccess(totalWorkouts: Int) {
        updateState { it.copy(totalWorkouts = totalWorkouts.toString()) }
    }

    private fun onGetTotalWorkoutsError(throwable: Throwable) {

    }

    private fun getTimeSpendPerWeek() {
        tryToCall(
            block = reportUseCase::getTimeSpendPerWeek,
            onSuccess = ::onGetTimeSpendPerWeekSuccess,
            onError = ::onGetTimeSpendPerWeekError
        )
    }

    private fun onGetTimeSpendPerWeekSuccess(timeSpendPerWeek: List<Pair<WorkoutDay, Long>>) {
        updateState {
            it.copy(
                timeSpendPerWeek = ReportScreenState.TimeSpendPerDay(
                day = timeSpendPerWeek.map { (day, _) -> day.name.take(3) },
                time = timeSpendPerWeek.map { (_, time) -> time }
            ))
        }
    }

    private fun onGetTimeSpendPerWeekError(throwable: Throwable) {

    }

    private fun getFocusedArea() {
        tryToCall(
            block = reportUseCase::getFocusedArea,
            onSuccess = ::onGetFocusedAreaSuccess,
            onError = ::onGetFocusedAreaError,
        )
    }

    private fun onGetFocusedAreaSuccess(focusedAreas: List<Pair<FocusArea, Int>>) {
        updateState {
            it.copy(
                mostTrainedMuscles = TrainedMuscle(
                    muscle = focusedAreas.map { (area, _) -> area.name },
                    percentage = focusedAreas.map { (_, percentage) -> percentage / 100f }
                )
            )
        }
    }

    private fun onGetFocusedAreaError(throwable: Throwable) {

    }

    override fun onViewAllHistoryWorkoutsClicked() {
        sendEffect(ReportEffect.navigateToAllHistoryWorkouts)
    }

    override fun onShareClicked() {
        sendEffect(ReportEffect.onShareClicked)
    }
    fun formatDuration(millis: Long): String {
        val totalSeconds = millis / 1000
        val totalMinutes = totalSeconds / 60
        val totalHours = totalMinutes / 60

        return when {
            totalHours > 0 -> "${totalHours}h"
            totalMinutes > 0 -> "${totalMinutes}min"
            else -> "${totalSeconds}s"
        }
    }
}