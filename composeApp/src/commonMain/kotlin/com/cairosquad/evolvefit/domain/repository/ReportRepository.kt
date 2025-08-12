package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.model.WeekDay

interface ReportRepository {
    suspend fun getTimeSpend(): Long
    suspend fun getTotalWorkouts(): Int
    suspend fun getTakenCalories(): Int
    suspend fun getExpectedCalories(): Int
    suspend fun getWaterTakenInLiter(): Float
    suspend fun getFocusedArea(): List<Pair<FocusArea, Int>>
    suspend fun getTimeSpendPerWeek(): List<Pair<WeekDay, Long>>
    suspend fun getWorkoutsPerWeek(): List<Pair<WeekDay, Int>>
}