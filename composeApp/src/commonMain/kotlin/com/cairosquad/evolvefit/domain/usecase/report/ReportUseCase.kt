package com.cairosquad.evolvefit.domain.usecase.report

import com.cairosquad.evolvefit.domain.ReportRepository
import com.cairosquad.evolvefit.entity.FocusArea
import com.cairosquad.evolvefit.entity.WorkoutDay

class ReportUseCase(
    private val reportRepository: ReportRepository,
) {

    suspend fun getTimeSpend(): Long {
        return reportRepository.getTimeSpend()
    }

    suspend fun getTotalWorkouts(): Int {
        return reportRepository.getTotalWorkouts()
    }

    suspend fun getTakenCalories(): Int {
        return reportRepository.getTakenCalories()
    }

    suspend fun getExpectedCalories(): Int {
        return reportRepository.getExpectedCalories()
    }

    suspend fun getWaterTakenInLiter(): Float {
        return reportRepository.getWaterTakenInLiter()
    }

    suspend fun getFocusedArea(): List<Pair<FocusArea, Int>> {
        return reportRepository.getFocusedArea()

    }

    suspend fun getTimeSpendPerWeek(): List<Pair<WorkoutDay, Long>> {
        return reportRepository.getTimeSpendPerWeek()
    }

    suspend fun getWorkoutsPerWeek(): List<Pair<WorkoutDay, Int>> {
        return reportRepository.getWorkoutsPerWeek()
    }
}