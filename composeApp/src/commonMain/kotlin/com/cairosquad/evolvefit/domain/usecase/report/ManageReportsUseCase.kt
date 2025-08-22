package com.cairosquad.evolvefit.domain.usecase.report

import com.cairosquad.evolvefit.domain.entity.Report
import com.cairosquad.evolvefit.domain.entity.WorkoutHistory
import com.cairosquad.evolvefit.domain.repository.ReportRepository
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository

class ManageReportsUseCase(
    private val reportRepository: ReportRepository,
    private val workoutRepository: WorkoutRepository
) {

    suspend fun getReport(startDate: String, endDate: String): Report {
        return reportRepository.getReport(startDate, endDate)
    }

    suspend fun getWorkoutHistory(): List<WorkoutHistory> {
        return workoutRepository.getWorkoutHistory()
    }
}