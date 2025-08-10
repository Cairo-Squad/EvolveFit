package com.cairosquad.evolvefit.remote.dto.nutrition

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWaterIntakeDto(
    @SerialName("totalWaterIntakeInLitre")
    val total: Float
)