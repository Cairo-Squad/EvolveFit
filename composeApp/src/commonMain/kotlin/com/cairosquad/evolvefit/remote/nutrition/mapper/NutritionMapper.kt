package com.cairosquad.evolvefit.remote.nutrition.mapper

import com.cairosquad.evolvefit.entity.nutrition.ConsumedMeal
import com.cairosquad.evolvefit.entity.nutrition.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.nutrition.DailyWaterSummary
import com.cairosquad.evolvefit.entity.nutrition.Meal
import com.cairosquad.evolvefit.entity.nutrition.MealType
import com.cairosquad.evolvefit.entity.nutrition.SuggestedMeal
import com.cairosquad.evolvefit.remote.nutrition.dto.ConsumedMealDto
import com.cairosquad.evolvefit.remote.nutrition.dto.ConsumedMealRequestDto
import com.cairosquad.evolvefit.remote.nutrition.dto.DailyCalorieSummaryDto
import com.cairosquad.evolvefit.remote.nutrition.dto.DailyWaterSummaryDto
import com.cairosquad.evolvefit.remote.nutrition.dto.MealDto
import com.cairosquad.evolvefit.remote.nutrition.dto.SuggestedMealDto

fun ConsumedMealDto.toDomain(): ConsumedMeal {
    return ConsumedMeal(
        id = id,
        name = mealName,
        type = MealType.valueOf(mealType),
        dateTime = date,
        calories = caloriesConsumed
    )
}
fun ConsumedMeal.toDto(): ConsumedMealRequestDto {
    return ConsumedMealRequestDto(
        mealName = name,
        mealType = type.name,
        consumedCalories = calories
    )
}
fun SuggestedMealDto.toDomain(): SuggestedMeal {
    return SuggestedMeal(
        id = id,
        name = name,
        type =type,
        calories = calories,
        imageUrl = imageUrl
    )
}
fun MealDto.toDomain(): Meal{
    return Meal(
        id = id,
        name =name,
        description =description,
        ingredients = ingredients,
        imageUrl = imageUrl,
        nutrition = Meal.Nutrition(
            calories = calories,
            fat = fat,
            protein = protein,
            carbs = carbs
        ),
        type = type
    )
}
fun DailyCalorieSummaryDto.toDomain(): DailyCalorieSummary {
    return DailyCalorieSummary(
        totalCalories = totalCalories,
        consumedCalories = consumedCalories
    )
}
fun DailyWaterSummaryDto.toDomain() = DailyWaterSummary(
    consumedWater = consumedWater,
    totalWater = this.totalWater
)