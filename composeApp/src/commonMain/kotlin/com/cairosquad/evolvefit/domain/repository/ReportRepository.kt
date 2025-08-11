package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.FocusArea
import com.cairosquad.evolvefit.domain.entity.WorkoutDay

interface ReportRepository {
    suspend fun getTimeSpend(): Long
    suspend fun getTotalWorkouts(): Int
    suspend fun getTakenCalories(): Int
    suspend fun getExpectedCalories(): Int
    suspend fun getWaterTakenInLiter(): Float
    suspend fun getFocusedArea(): List<Pair<FocusArea, Int>>
    suspend fun getTimeSpendPerWeek(): List<Pair<WorkoutDay, Long>>
    suspend fun getWorkoutsPerWeek(): List<Pair<WorkoutDay, Int>>
}