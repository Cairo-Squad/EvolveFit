package com.cairosquad.evolvefit.domain.model

data class NutritionProgress<T : Number>(
    val goal: T,
    val currentProgress: T
)