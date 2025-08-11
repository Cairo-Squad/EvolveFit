package com.cairosquad.evolvefit.remote.nutrition.mapper

import com.cairosquad.evolvefit.entity.DailyWaterSummary
import com.cairosquad.evolvefit.remote.nutrition.dto.DailyWaterSummaryDto

fun DailyWaterSummaryDto.toDomain() = DailyWaterSummary(
    consumedWater = consumedWater,
    totalWater = this.totalWater
)