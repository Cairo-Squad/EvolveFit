package com.cairosquad.evolvefit.domain.usecase.report

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.ReportRepository

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

    suspend fun getTimeSpendPerWeek(): List<Pair<WeekDay, Long>> {
        return reportRepository.getTimeSpendPerWeek()
    }

    suspend fun getWorkoutsPerWeek(): List<Pair<WeekDay, Int>> {
        return reportRepository.getWorkoutsPerWeek()
    }
}