package com.cairosquad.evolvefit.repository.report.remote

import com.cairosquad.evolvefit.domain.entity.Report
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.repository.report.remote.dto.NutritionReportDto
import com.cairosquad.evolvefit.repository.report.remote.dto.WorkoutReportDto

fun reportDtoToReport(
    nutritionReport: NutritionReportDto,
    workoutReport: WorkoutReportDto
): Report {
    return Report(
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