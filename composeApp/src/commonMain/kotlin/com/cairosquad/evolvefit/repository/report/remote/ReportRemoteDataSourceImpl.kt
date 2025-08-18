package com.cairosquad.evolvefit.repository.report.remote

import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.report.dto.NutritionReportDto
import com.cairosquad.evolvefit.repository.report.dto.WorkoutReportDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ReportRemoteDataSourceImpl(
    private val httpClient: HttpClient

): ReportRemoteDataSource {
    override suspend fun getWorkoutReport(
        startDate: String,
        endDate: String
    ): WorkoutReportDto {
        return callApi<WorkoutReportDto> {
            httpClient.get("$REPORT_PATH/workout"){
                parameter(key = START_DATE, value = startDate)
                parameter(key = END_DATE, value = endDate)
            }.body()
        }
    }

    override suspend fun getNutritionReport(
        startDate: String,
        endDate: String
    ): NutritionReportDto {
        return callApi<NutritionReportDto> {
            httpClient.get("$REPORT_PATH/nutrition"){
                parameter(key = START_DATE, value = startDate)
                parameter(key = END_DATE, value = endDate)
            }.body()
        }
    }

    private companion object {
        const val REPORT_PATH = "report"
        const val START_DATE = "startDate"
        const val END_DATE = "endDate"
    }
}