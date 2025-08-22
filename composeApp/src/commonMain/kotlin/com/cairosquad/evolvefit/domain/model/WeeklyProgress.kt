package com.cairosquad.evolvefit.domain.model

data class WeeklyProgress(
    val weeklyProgressChecks: Map<Int, Boolean>,
    val activityPercentage: Int,
)