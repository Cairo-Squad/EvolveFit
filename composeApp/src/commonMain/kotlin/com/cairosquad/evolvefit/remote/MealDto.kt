package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.entity.Meal
import com.cairosquad.evolvefit.entity.MealType
import kotlinx.serialization.Serializable

@Serializable
data class MealDto(
    val id: String,
    val userId: String,
    val date: String,
    val caloriesConsumed: Int,
    val mealName: String,
    val mealType: String
)
fun MealDto.toEntity(): Meal {
    return Meal(
        id = id.hashCode().toLong(),
        name = mealName,
        type = MealType.valueOf(mealType),
        dateTime = date,
        calories = caloriesConsumed
    )
}