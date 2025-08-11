package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.entity.FocusArea
import com.cairosquad.evolvefit.domain.entity.WorkoutDay
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
            Pair(FocusArea.Quadriceps, 10),
            Pair(FocusArea.LowerBack, 35),
            Pair(FocusArea.Shoulders, 40),
            Pair(FocusArea.Abs, 5),
            Pair(FocusArea.Core, 9),
            Pair(FocusArea.Calves, 7),
        )
    }

    override suspend fun getTimeSpendPerWeek(): List<Pair<WorkoutDay, Long>> {
        delay(2000)
        return listOf(
            Pair(WorkoutDay.MONDAY, 10),
            Pair(WorkoutDay.TUESDAY, 35),
            Pair(WorkoutDay.WEDNESDAY, 40),
            Pair(WorkoutDay.THURSDAY, 5),
            Pair(WorkoutDay.FRIDAY, 9),
            Pair(WorkoutDay.SATURDAY, 7),
            Pair(WorkoutDay.SUNDAY, 10),
        )
    }

    override suspend fun getWorkoutsPerWeek(): List<Pair<WorkoutDay, Int>> {
        delay(2000)
        return listOf(
            Pair(WorkoutDay.MONDAY, 10),
            Pair(WorkoutDay.TUESDAY, 35),
            Pair(WorkoutDay.WEDNESDAY, 40),
            Pair(WorkoutDay.THURSDAY, 5),
            Pair(WorkoutDay.FRIDAY, 9),
            Pair(WorkoutDay.SATURDAY, 7),
            Pair(WorkoutDay.SUNDAY, 10),
        )
    }
}