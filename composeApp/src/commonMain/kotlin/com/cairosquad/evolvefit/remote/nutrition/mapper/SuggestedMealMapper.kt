package com.cairosquad.evolvefit.remote.nutrition.mapper

import com.cairosquad.evolvefit.entity.SuggestedMeal
import com.cairosquad.evolvefit.remote.nutrition.dto.SuggestedMealDto

fun SuggestedMealDto.toDomain(): SuggestedMeal{
    return SuggestedMeal(
        id = id,
        name = name,
        calories = calories,
        imageUrl = imageUrl,
        type = type
    )
}