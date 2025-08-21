package com.cairosquad.evolvefit.domain.usecase.home.model

data class WeeklyProgress(
    val weeklyProgressChecks: Map<Int, Boolean>,
    val activityPercentage: Int,
)