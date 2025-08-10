package com.cairosquad.evolvefit.remote.mapper

import com.cairosquad.evolvefit.entity.DailyWaterIntake
import com.cairosquad.evolvefit.remote.dto.nutrition.DailyWaterIntakeDto

fun DailyWaterIntakeDto.toDomain() = DailyWaterIntake(
    total = this.total
)