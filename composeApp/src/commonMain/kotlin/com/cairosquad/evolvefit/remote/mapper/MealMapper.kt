package com.cairosquad.evolvefit.remote.mapper

import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.MealType
import com.cairosquad.evolvefit.remote.dto.nutrition.ConsumedMealDto
import com.cairosquad.evolvefit.remote.dto.nutrition.MealRequestDto

fun ConsumedMealDto.toEntity(): ConsumedMeal {
    return ConsumedMeal(
        id = id,
        name = mealName,
        type = MealType.valueOf(mealType),
        dateTime = date,
        calories = caloriesConsumed
    )
}
fun ConsumedMeal.toRequestDto(): MealRequestDto {
    return MealRequestDto(
        mealName = name,
        mealType = type.name,
        caloriesConsumed = calories
    )
}