package com.cairosquad.evolvefit.domain.usecase.home.model

data class NutritionUpdate(
    val caloriesProgress: NutritionProgress<Int>,
    val waterProgress: NutritionProgress<Float>,
) {
    data class NutritionProgress<T : Number>(
        val goal: T,
        val currentProgress: T
    )
}
