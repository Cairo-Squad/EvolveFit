package com.cairosquad.evolvefit.repository.report

import com.cairosquad.evolvefit.domain.entity.Report
import com.cairosquad.evolvefit.domain.repository.ReportRepository
import com.cairosquad.evolvefit.repository.report.remote.dto.NutritionReportDto
import com.cairosquad.evolvefit.repository.report.remote.dto.WorkoutReportDto
import com.cairosquad.evolvefit.repository.report.remote.reportDtoToReport
import com.cairosquad.evolvefit.repository.report.remote.ReportRemoteDataSource

class ReportRepositoryImpl(
    private val reportRemoteDataSource: ReportRemoteDataSource,
) : ReportRepository {
    override suspend fun getReport(startDate: String, endDate: String): Report {
        val workoutReport = getWorkoutReport(startDate, endDate)
        val nutritionReport = getNutritionReport(startDate, endDate)

        return reportDtoToReport(nutritionReport, workoutReport)
    }

    private suspend fun getWorkoutReport(startDate: String, endDate: String): WorkoutReportDto {
        return reportRemoteDataSource.getWorkoutReport(startDate, endDate)
    }

    private suspend fun getNutritionReport(startDate: String, endDate: String): NutritionReportDto {
        return reportRemoteDataSource.getNutritionReport(startDate, endDate)
    }
}