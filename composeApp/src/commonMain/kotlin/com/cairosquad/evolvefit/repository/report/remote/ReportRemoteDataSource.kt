package com.cairosquad.evolvefit.repository.report.remote

import com.cairosquad.evolvefit.repository.report.dto.NutritionReportDto
import com.cairosquad.evolvefit.repository.report.dto.WorkoutReportDto

interface ReportRemoteDataSource {

    suspend fun getWorkoutReport(startDate: String, endDate: String): WorkoutReportDto

    suspend fun getNutritionReport(startDate: String, endDate: String): NutritionReportDto
}