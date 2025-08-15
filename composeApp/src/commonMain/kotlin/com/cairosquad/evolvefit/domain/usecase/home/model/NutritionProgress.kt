package com.cairosquad.evolvefit.domain.usecase.home.model

data class NutritionProgress<T : Number>(
    val goal: T,
    val currentProgress: T
)