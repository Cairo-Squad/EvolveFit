package com.cairosquad.evolvefit.remote.nutrition.mapper

import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.MealType
import com.cairosquad.evolvefit.remote.nutrition.dto.ConsumedMealDto
import com.cairosquad.evolvefit.remote.nutrition.dto.ConsumedMealRequestDto

fun ConsumedMealDto.toEntity(): ConsumedMeal {
    return ConsumedMeal(
        id = id,
        name = mealName,
        type = MealType.valueOf(mealType),
        dateTime = date,
        calories = caloriesConsumed
    )
}
fun ConsumedMeal.toRequestDto(): ConsumedMealRequestDto {
    return ConsumedMealRequestDto(
        mealName = name,
        mealType = type.name,
        consumedCalories = calories
    )
}