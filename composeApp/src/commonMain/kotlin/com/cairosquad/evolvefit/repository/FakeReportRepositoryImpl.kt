package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.ReportRepository
import kotlinx.coroutines.delay

class FakeReportRepositoryImpl : ReportRepository {
    override suspend fun getTimeSpend(): Long {
        // 32 hours
        delay(2000)
        return 32L * 60 * 60 * 1000
    }

    override suspend fun getTotalWorkouts(): Int {
        delay(2000)
        return 15
    }

    override suspend fun getTakenCalories(): Int {
        delay(2000)
        return 4500
    }

    override suspend fun getExpectedCalories(): Int {
        delay(2000)
        return 10450
    }

    override suspend fun getWaterTakenInLiter(): Float {
        delay(2000)
        return 4.5f
    }

    override suspend fun getFocusedArea(): List<Pair<FocusArea, Int>> {
        delay(2000)
        return listOf(
            Pair(FocusArea.QUADRICEPS, 10),
            Pair(FocusArea.ABS, 35),
            Pair(FocusArea.CALVES, 40),
            Pair(FocusArea.LOWER_BACK, 5),
            Pair(FocusArea.CORE, 9),
            Pair(FocusArea.SHOULDERS, 7),
        )
    }

    override suspend fun getTimeSpendPerWeek(): List<Pair<WeekDay, Long>> {
        delay(2000)
        return listOf(
            Pair(WeekDay.MONDAY, 10),
            Pair(WeekDay.TUESDAY, 35),
            Pair(WeekDay.WEDNESDAY, 40),
            Pair(WeekDay.THURSDAY, 5),
            Pair(WeekDay.FRIDAY, 9),
            Pair(WeekDay.SATURDAY, 7),
            Pair(WeekDay.SUNDAY, 10),
        )
    }

    override suspend fun getWorkoutsPerWeek(): List<Pair<WeekDay, Int>> {
        delay(2000)
        return listOf(
            Pair(WeekDay.MONDAY, 10),
            Pair(WeekDay.TUESDAY, 35),
            Pair(WeekDay.WEDNESDAY, 40),
            Pair(WeekDay.THURSDAY, 5),
            Pair(WeekDay.FRIDAY, 9),
            Pair(WeekDay.SATURDAY, 7),
            Pair(WeekDay.SUNDAY, 10),
        )
    }
}