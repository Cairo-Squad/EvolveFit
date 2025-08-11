package com.cairosquad.evolvefit.remote.nutrition.mapper

import com.cairosquad.evolvefit.entity.SuggestedMeal
import com.cairosquad.evolvefit.entity.SuggestedMealDetails
import com.cairosquad.evolvefit.remote.nutrition.dto.SuggestedMealDetailsDto
import com.cairosquad.evolvefit.remote.nutrition.dto.SuggestedMealDto

fun SuggestedMealDetailsDto.toDomain(): SuggestedMealDetails {
    return SuggestedMealDetails(
        name = name,
        description = description,
        calories = calories,
        carbs = carbs,
        protein = protein,
        fat = fat,
        type = type,
        ingredients = ingredients,
        imageUrl = imageUrl
    )
}