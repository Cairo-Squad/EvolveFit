package com.cairosquad.evolvefit.remote.mapper

import com.cairosquad.evolvefit.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.remote.dto.nutrition.DailyCalorieSummaryDto

fun DailyCalorieSummaryDto.toEntity(): DailyCalorieSummary {
    return DailyCalorieSummary(
        totalCalories = totalCalories,
        caloriesConsumed = caloriesConsumed
    )
}