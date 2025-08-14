package com.cairosquad.evolvefit.domain.usecase.home.model

import com.cairosquad.evolvefit.domain.entity.Profile.FitnessGoal
import com.cairosquad.evolvefit.domain.model.MeasurementStandard

data class WeeklyProgress(
    val weeklyProgressChecks: Map<Int, Boolean>,
    val userGoal: FitnessGoal,
    val measurementUnit: MeasurementStandard,
    val currentWeight: Float,
    val activityPercentage: Int,
)