package com.cairosquad.evolvefit.domain.model

import com.cairosquad.evolvefit.domain.entity.Profile

data class WeeklyProgress(
    val weeklyProgressChecks: Map<Int, Boolean>,
    val userGoal: Profile.FitnessGoal,
    val measurementUnit: MeasurementStandard,
    val currentWeight: Float,
    val activityPercentage: Int,
)