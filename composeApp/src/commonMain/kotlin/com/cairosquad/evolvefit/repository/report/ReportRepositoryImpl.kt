package com.cairosquad.evolvefit.repository.report

import com.cairosquad.evolvefit.domain.entity.Report
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.ReportRepository
import com.cairosquad.evolvefit.repository.report.dto.NutritionReportDto
import com.cairosquad.evolvefit.repository.report.dto.WorkoutReportDto
import com.cairosquad.evolvefit.repository.report.remote.ReportRemoteDataSource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class ReportRepositoryImpl(
    private val remoteDataSource: ReportRemoteDataSource,
) : ReportRepository {
    override suspend fun getReport(startDate: String, endDate: String): Report = coroutineScope {
        val workoutReportDeferred = async { getWorkoutReport(startDate, endDate) }
        val nutritionReportDeferred = async { getNutritionReport(startDate, endDate) }

        val workoutReport = workoutReportDeferred.await()
        val nutritionReport = nutritionReportDeferred.await()

        Report(
            takenCaloriesInKcal = nutritionReport.caloriesConsumed,
            expectedCalories = nutritionReport.totalCalories,
            waterTakenInLiter = nutritionReport.waterConsumed.toFloat(),
            timeSpentInSeconds = workoutReport.totalTimeSpentSeconds,
            totalWorkouts = workoutReport.totalWorkouts,
            focusedAreas = workoutReport.topFocusAreas.map { FocusArea.valueOf(it.area) to it.percentage },
            timeSpentPerWeek = workoutReport.totalTimeSpentByDay.map { WeekDay.valueOf(it.day) to it.timeInSeconds },
            workoutsPerWeek = workoutReport.workoutsByDay.map { WeekDay.valueOf(it.day) to it.workoutsCount }
        )
    }

    private suspend fun getWorkoutReport(startDate: String, endDate: String): WorkoutReportDto {
        return remoteDataSource.getWorkoutReport(startDate, endDate)
    }

    private suspend fun getNutritionReport(startDate: String, endDate: String): NutritionReportDto {
        return remoteDataSource.getNutritionReport(startDate, endDate)
    }
}