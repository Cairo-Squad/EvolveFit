package com.cairosquad.evolvefit.repository.nutrition.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWaterSummaryDto(
    @SerialName("waterConsumed")
    val consumedWater: Float,
    @SerialName("totalWater")
    val totalWater: Float
)