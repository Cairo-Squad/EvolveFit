package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.entity.Report
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.ReportRepository
import kotlinx.coroutines.delay

class FakeReportRepositoryImpl : ReportRepository {

    override suspend fun getReport(): Report {
        delay(2000)
        return Report(
            timeSpentInMilliSeconds = getTimeSpend(),
            totalWorkouts = getTotalWorkouts(),
            takenCaloriesInKcal = getTakenCalories(),
            expectedCalories = getExpectedCalories(),
            waterTakenInLiter = getWaterTakenInLiter(),
            focusedAreas = getFocusedArea(),
            timeSpendPerWeek = getTimeSpendPerWeek(),
            workoutsPerWeek = getWorkoutsPerWeek(),
        )
    }

    private fun getTimeSpend(): Long {
        // 32 hours
        return 32L * 60 * 60 * 1000
    }

    private fun getTotalWorkouts(): Int {
        return 15
    }

    private fun getTakenCalories(): Int {
        return 4500
    }

    private fun getExpectedCalories(): Int {
        return 10450
    }

    private fun getWaterTakenInLiter(): Float {
        return 4.5f
    }

    private fun getFocusedArea(): List<Pair<FocusArea, Int>> {
        return listOf(
            Pair(FocusArea.QUADRICEPS, 10),
            Pair(FocusArea.ABS, 35),
            Pair(FocusArea.CALVES, 40),
            Pair(FocusArea.LOWER_BACK, 5),
            Pair(FocusArea.CORE, 9),
            Pair(FocusArea.SHOULDERS, 7),
        )
    }

    private fun getTimeSpendPerWeek(): List<Pair<WeekDay, Long>> {
        return listOf(
            Pair(WeekDay.SATURDAY, 7),
            Pair(WeekDay.SUNDAY, 10),
            Pair(WeekDay.MONDAY, 10),
            Pair(WeekDay.TUESDAY, 35),
            Pair(WeekDay.WEDNESDAY, 40),
            Pair(WeekDay.THURSDAY, 5),
            Pair(WeekDay.FRIDAY, 9),
        )
    }

     private fun getWorkoutsPerWeek(): List<Pair<WeekDay, Int>> {
        return listOf(
            Pair(WeekDay.SATURDAY, 7),
            Pair(WeekDay.SUNDAY, 10),
            Pair(WeekDay.MONDAY, 10),
            Pair(WeekDay.TUESDAY, 35),
            Pair(WeekDay.WEDNESDAY, 40),
            Pair(WeekDay.THURSDAY, 5),
            Pair(WeekDay.FRIDAY, 9),
        )
    }
}