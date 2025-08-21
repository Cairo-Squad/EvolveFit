package com.cairosquad.evolvefit.repository.nutrition.remote

import com.cairosquad.evolvefit.domain.entity.ConsumedMeal
import com.cairosquad.evolvefit.domain.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.domain.entity.DailyWaterSummary
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.domain.model.MealType
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.ConsumedMealDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.ConsumedMealRequestDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.DailyCalorieSummaryDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.DailyWaterSummaryDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.FavouriteMealDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.MealDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.SuggestedMealDto

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
fun FavouriteMealDto.toDomain(): SuggestedMeal {
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
            caloriesInKcal = calories,
            fatInGrams = fat,
            proteinInGrams = protein,
            carbsInGrams = carbs,
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