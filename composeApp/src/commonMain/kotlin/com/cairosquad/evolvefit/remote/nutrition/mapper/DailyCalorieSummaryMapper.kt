package com.cairosquad.evolvefit.remote.nutrition.mapper

import com.cairosquad.evolvefit.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.remote.nutrition.dto.DailyCalorieSummaryDto

fun DailyCalorieSummaryDto.toEntity(): DailyCalorieSummary {
    return DailyCalorieSummary(
        totalCalories = totalCalories,
        consumedCalories = consumedCalories
    )
}